import React, {useState} from "react";
import { ApiClient } from "@/features/shared/api/api-client.ts";
import {Button} from "@/components/ui/button.tsx"
import {Input} from "@/components/ui/input.tsx"
import {Label} from "@/components/ui/label.tsx"
import {DateTimePicker} from "@/components/date-time/date-time-picker.tsx";
import {Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger, DialogDescription} from "@/components/ui/dialog.tsx";
import EventTypeSelector from "@/features/event/components/checkboxes/event-type-selector.tsx";


interface DateTimeRange{
    from: Date | undefined;
    to: Date | undefined;
}

export function CreateEvent() {
    const apiClient = new ApiClient("http://127.0.0.1");

    const [open, setDialogOpen] = useState(false);
    const [eventName, setEventName] = useState("");
    const [eventType, setEventType] = useState<{id: number; eventTypeName: string} | null>(null);
    const [dateRange, setDateRange] = useState<DateTimeRange>({ from: undefined, to: undefined });

    async function handleSubmit(): Promise<void> {
        if (!eventType) {
            console.error("event type not selected.");
            return;
        }

        try {
            const payload = {
                id: null,
                name: eventName,
                eventType: {
                    id: eventType.id,
                    name: eventType?.eventTypeName,
                },
                startDateTime: dateRange.from ? dateRange.from.toISOString() : undefined,
                endDateTime: dateRange.to ? dateRange.to.toISOString() : undefined,
            };

            const response = await apiClient.post("/events", payload);
            console.log("Event created:", response);
        } catch (err) {
            console.log("Error creating event:", err);
        }
    }

    return (
        <Dialog open={open} onOpenChange={setDialogOpen}>
            <DialogTrigger asChild>
                <Button variant="ghost" className="w-full justify-start">+ Create New Event</Button>
            </DialogTrigger>

            <DialogContent className="max-w-3xl">
                <DialogHeader>
                    <DialogTitle>📆 Create Event</DialogTitle>
                    <DialogDescription>Enter the details for your new event.</DialogDescription>
                </DialogHeader>

                <div className="flex flex-col gap-4">
                    <div className="flex flex-col space-y-2">
                        <Label htmlFor="eventName">Event Name</Label>
                        <Input
                            id="eventName"
                            placeholder="Enter Event"
                            value={eventName}
                            onChange={(e) => setEventName(e.target.value)}
                        />
                    </div>

                    <EventTypeSelector
                        title="Event Type"
                        selectedType={eventType}
                        onChange={(type) => setEventType(type)}
                    />

                    <DateTimePicker
                        value={dateRange}
                        onChange={(range: React.SetStateAction<DateTimeRange>) => setDateRange(range)}
                        label="Event Time"
                        doesGetTime={false}
                    />

                    <div className="flex flex-col space-y-2" >
                        <Button
                            className="cursor-pointer"
                            onClick={handleSubmit}>Create</Button>
                    </div>
                </div>

                <p className="text-sm text-muted-foreground mt-4">
                    Don’t worry about getting the details correct now — you can always edit your event later.
                </p>
            </DialogContent>
        </Dialog>
    );
}

export default CreateEvent;