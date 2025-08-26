import React from "react";
import { Checkbox } from "@/components/ui/checkbox";
import { Label } from "@/components/ui/label";

interface EventType {
    id: number;
    eventTypeName: string;
}

interface EventTypeSelectorProps {
    title: string;
    selectedType?: EventType | null;
    onChange: (type: EventType | null) => void;
}

const eventTypes = [
    { id: 1, eventTypeName: "Holiday" },
    { id: 2, eventTypeName: "Single-Venue" },
    { id: 3, eventTypeName: "Multi-Venue" },
    { id: 4, eventTypeName: "Business Meeting" },
];

export const EventTypeSelector: React.FC<EventTypeSelectorProps> = ({ title, selectedType, onChange }) => {
    return (
        <div>
            <Label className="pb-3 space-y-2">{title}</Label>
            <div className="flex items-center gap-6">
                {eventTypes.map(({ id, eventTypeName }) => (
                    <div key={id} className="flex items-center space-x-2">
                        <Checkbox
                            id={id.toString()}
                            checked={selectedType?.id === id}
                            onCheckedChange={(checked) => {
                                if (checked) {
                                    onChange({ id, eventTypeName });
                                } else {
                                    onChange(null);
                                }
                            }}
                        />
                        <Label htmlFor={id.toString()}>{eventTypeName}</Label>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default EventTypeSelector;