package com.thingplanner.backend.events.feature;


@RestController
@RequestMapping("/events")
class GetEventApi {
    private final GetEventService getEventService;


    public GetEventApi(GetEventService getEventService) {
        this.getEventService = getEventService;
    }

    @RequestMapping("/get")
    public ResponseEntity<?> getEvent(@Valid @RequestBody GetEventRequest request) {
        try {
            return getEventService.get(request);
        } catch (RuntimeException e) {
            return new MessageResponse("Failure", "Could not get events by specified criteria");
        }
    }

    @RequestMapping("/get{id}")
    public getEventById(@RequestParam UUID id) {
        try {
            return getEventService.getById(id);
        } catch (RuntimeException e) {
            return new MessageResponse("Failure", String.format("Could not get event with ID: %s", id));
        }
    }
}