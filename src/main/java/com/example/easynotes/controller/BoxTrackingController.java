package com.example.easynotes.controller;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Box;
import com.example.easynotes.model.BoxEvent;
import com.example.easynotes.model.BoxState;
import com.example.easynotes.model.Location;
import com.example.easynotes.repository.BoxEventRepository;
import com.example.easynotes.repository.BoxRepository;
import com.example.easynotes.repository.BoxStateRepository;
import com.examples.box.initialData.InitialData;
import com.examples.box.initialData.ResultData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by DeepankarShukla.
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class BoxTrackingController {

	private static final Logger LOG = LoggerFactory.getLogger(BoxTrackingController.class);

	@Autowired
	BoxEventRepository boxEventRepository;

	@Autowired
	BoxRepository boxRepository;

	@Autowired
	BoxStateRepository boxStateRepository;

	@GetMapping("/boxes")
	public List<BoxEvent> getAllBoxEvents() {
		return boxEventRepository.findAll();
	}

	@GetMapping("/boxStates")
	public List<BoxState> getAllBoxStates() {
		return boxStateRepository.findAll();
	}

	@PostMapping("/updateBoxes")
	public BoxEvent createBoxEvent(@Valid @RequestBody BoxEvent boxEvent) {				 
		if (!StringUtils.isEmpty(boxEvent.getToBox())) {
			List<BoxState> boxStates = boxStateRepository.findBoxState(boxEvent.getFromBox());

			if (!org.springframework.util.CollectionUtils.isEmpty(boxStates)) {
				for (BoxState state : boxStates) {

					String insertionState = state.getInsertionState();
					if (boxEvent.getPackageType().equalsIgnoreCase("P")) {
						if (insertionState.endsWith(boxEvent.getFromBox())) {
							insertionState = insertionState + "<" + boxEvent.getToBox();
							state.setInsertionState(insertionState);

						} else {
							return createErrorData("Box is alredy inside another box");
						}

					} else {
						if (state.getInsertionState().contains(boxEvent.getToBox())) {
							List<String> lastElement = Arrays.asList(insertionState.split("<"));
							if (lastElement.get(lastElement.size() - 1).equalsIgnoreCase(boxEvent.getToBox())) {
								insertionState = insertionState.replaceAll("<" + boxEvent.getToBox(), "");
							} else {
								return createErrorData(
										"Box Can not be removed as this box is not inside mentioned box");
							}
							updateRequestBoxLocations(boxEvent);
							state.setInsertionState(insertionState);
						} else {
							return createErrorData("Box Can not be removed");
						}

					}
					updateHierarchialBoxLocation(boxEvent, state);
					boxStateRepository.save(state);

				}
			} else if (boxEvent.getPackageType().equalsIgnoreCase("p")) {
				BoxState state = new BoxState();
				state.setInsertionState(boxEvent.getFromBox() + "<" + boxEvent.getToBox());
				updateRequestBoxLocations(boxEvent);
				boxStateRepository.save(state);
			} else {
				return createErrorData("Box Can not be removed");
			}

		} else {
			Box frombox = boxRepository.findById(Long.parseLong(boxEvent.getFromBox()))
					.orElseThrow(() -> new ResourceNotFoundException("Note", "id", boxEvent.getFromBox()));
			frombox.setLocation(boxEvent.getLocation());
			boxRepository.save(frombox);
		}

		return boxEventRepository.save(boxEvent);
	}

	private BoxEvent createErrorData(String message) {
		BoxEvent errorEvent = new BoxEvent();
		errorEvent.setStatus("fail");
		errorEvent.setErrorMessage(message);
		return errorEvent;
	}

	private void updateRequestBoxLocations(BoxEvent boxEvent) {
		Box frombox = boxRepository.findById(Long.parseLong(boxEvent.getFromBox()))
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", boxEvent.getFromBox()));
		Box toBox = boxRepository.findById(Long.parseLong(boxEvent.getToBox()))
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", boxEvent.getToBox()));
		frombox.setLocation(boxEvent.getLocation());
		toBox.setLocation(boxEvent.getLocation());
		boxRepository.save(frombox);
		boxRepository.save(toBox);
	}

	private void updateHierarchialBoxLocation(BoxEvent boxEvent, BoxState state) {
		List<String> locationUpdateionList = Arrays.asList(state.getInsertionState().split("<"));
		for (String originalBox : locationUpdateionList) {
			Box originalBoxModel = boxRepository.findById(Long.parseLong(originalBox))
					.orElseThrow(() -> new ResourceNotFoundException("Note", "id", originalBox));
			originalBoxModel.setLocation(boxEvent.getLocation());
			boxRepository.save(originalBoxModel);
		}
	}

	@PostMapping("/boxState")
	public BoxState createBoxState(@Valid @RequestBody BoxState boxState) {
		return boxStateRepository.save(boxState);
	}

	@GetMapping("/box")
	public List<Box> getAllBoxes() {
		return boxRepository.findAll();
	}

	@GetMapping("/search")
	public ResultData search(@RequestParam(value = "boxId", required = false) String boxId,
			@RequestParam(value = "timestamp", required = false) String timestamp) {		 
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		List<BoxEvent> boxEvents = new ArrayList<>();
		List<String> uniqueBoxId = new ArrayList<>();
		if (!StringUtils.isEmpty(boxId)) {
			List<BoxState> boxStates = boxStateRepository.findBoxState(boxId);
			if (CollectionUtils.isEmpty(boxStates)) {
				uniqueBoxId.add(boxId);
			}
			for (BoxState boxState : boxStates) {
				uniqueBoxId.addAll(Arrays.asList(boxState.getInsertionState().split("<")));
			}
		}
		if (timestamp != null && !StringUtils.isEmpty(boxId)) {
			try {
				boxEvents = boxEventRepository.findBoxEventsByTimestampAndBoxId(
						uniqueBoxId.stream().distinct().collect(Collectors.toList()), inputFormatter.parse(timestamp));
			} catch (ParseException e) {
				LOG.info(e.getMessage());
			}
		} else if (timestamp != null) {
			try {
				boxEvents = boxEventRepository.findBoxEventsByTimestamp(inputFormatter.parse(timestamp));				
			} catch (ParseException e) {
				LOG.info(e.getMessage());
			}
		} else if (!StringUtils.isEmpty(boxId)) {
			boxEvents = boxEventRepository.findBoxEvents(uniqueBoxId.stream().distinct().collect(Collectors.toList()));
		} else {
			boxEvents = boxEventRepository.findAll();
		}
		return createResultData(boxId, simpleDateFormat, boxEvents);
		 
	}

	private ResultData createResultData(String boxId, SimpleDateFormat simpleDateFormat, List<BoxEvent> boxEvents) {
		ResultData results = new ResultData();
		List<String> resultSummary = new ArrayList<>();
		List<String> boxLocation = new ArrayList<>();
		List<String> emptyBoxlIst = new ArrayList<>();
		for (BoxEvent event : boxEvents) {
			String dateString = simpleDateFormat.format(event.getTimestamp());

			if (StringUtils.isEmpty(event.getToBox())) {
				resultSummary.add(
						"Box " + event.getFromBox() + " is at " + event.getLocation().toString() + " at " + dateString);
			} else if (event.getPackageType().toLowerCase().equalsIgnoreCase("P")) {

				if (!CollectionUtils.isEmpty(boxLocation)
						&& boxLocation.get(boxLocation.size() - 1).equalsIgnoreCase(event.getFromBox())) {
					boxLocation.add(event.getToBox());
				} else {
					boxLocation.add(event.getFromBox());
					boxLocation.add(event.getToBox());
				}
				resultSummary.add("Box " + event.getFromBox() + " has been put inside Box " + event.getToBox() + " at "
						+ event.getLocation().toString() + " at " + dateString);
			} else {
				boxLocation.remove(event.getToBox());
				resultSummary.add("Box " + event.getFromBox() + " has been removed from " + event.getToBox() + " at "
						+ event.getLocation().toString() + " at " + dateString);
				emptyBoxlIst.add(event.getToBox());
			}
		}
		List<String> boxState = new ArrayList<>();

		for (int index = 0; index < boxLocation.size() - 1; index++) {

			boxState.add("Box " + boxLocation.get(index) + " is inside Box " + boxLocation.get(index + 1));
		}
		for (int index = 0; index < emptyBoxlIst.size(); index++) {
			boxState.add("Box " + emptyBoxlIst.get(index) + " is empty");
		}
		if (!StringUtils.isEmpty(boxId)) {
			Box queriedBox = boxRepository.findById(Long.parseLong(boxId))
					.orElseThrow(() -> new ResourceNotFoundException("Note", "id", boxId));
			results.setCurrentLocation(queriedBox.getLocation());
		}
		results.setBoxState(boxState);
		results.setActions(resultSummary);
		return results;
	}

	@PostMapping("/box")
	public Box createBox(@Valid @RequestBody Box box) {
		return boxRepository.save(box);
	}

	@GetMapping("/box/{id}")
	public Box getBoxById(@PathVariable(value = "id") Long boxId) {
		return boxRepository.findById(boxId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", boxId));
	}

	@GetMapping("/boxes/{id}")
	public BoxEvent getBoxEventById(@PathVariable(value = "id") Long boxId) {
		return boxEventRepository.findById(boxId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", boxId));
	}

	@PutMapping("/boxes/{id}")
	public BoxEvent updateBox(@PathVariable(value = "id") Long boxId, @Valid @RequestBody BoxEvent boxDetails) {

		BoxEvent boxEvent = boxEventRepository.findById(boxId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", boxId));

		return boxEventRepository.save(boxEvent);

	}

	@DeleteMapping("/boxes/{id}")
	public ResponseEntity<?> deleteBox(@PathVariable(value = "id") Long boxId) {
		BoxEvent boxEvent = boxEventRepository.findById(boxId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", boxId));

		boxEventRepository.delete(boxEvent);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/boxesState/{id}")
	public ResponseEntity<?> deleteBoxState(@PathVariable(value = "id") Long boxId) {
		BoxState boxState = boxStateRepository.findById(boxId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", boxId));

		boxStateRepository.delete(boxState);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/initialData")
	public InitialData getInitialData() {
		InitialData initialData = new InitialData();
		initialData.setBoxes(boxRepository.findAll());
		initialData.setLocation(Arrays.asList(Location.values()));
		return initialData;
	}

}
