import React, {useEffect, useState} from "react";
import { format } from "date-fns";
import { Calendar } from "@/components/ui/calendar";
import { Popover, PopoverTrigger, PopoverContent } from "@/components/ui/popover";
import { cn } from "@/lib/utils";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

interface DateTimeRange {
    from: Date | undefined;
    to: Date | undefined;
}

interface DateTimePickerProps {
    value?: DateTimeRange;
    onChange?: (value: DateTimeRange) => void;
    label?: string;
    className?: string;
    doesGetTime?: boolean;
}

export const DateTimePicker: React.FC<DateTimePickerProps> = ({
                                                                  value,
                                                                  onChange,
                                                                  label,
                                                                  className,
                                                                  doesGetTime,
                                                              }) => {
    const [open, setOpen] = useState(false);

    const [range, setRange] = useState<DateTimeRange>({
        from: value?.from || undefined,
        to: value?.to || undefined,
    });

    const [hours, setHours] = useState(0);
    const [minutes, setMinutes] = useState(0);

    useEffect (() => {
        if (value?.from) {
            setHours(value.from.getHours());
            setMinutes(value.from.getMinutes());
        }
        setRange({
            from: value?.from,
            to: value?.to,
        });
    }, [value]);

    const updateDateRange = (newRange: DateTimeRange) => {
        if (!newRange.from) return;

        const from = new Date(newRange.from);
        const to = newRange.to ? new Date(newRange.to) : undefined;

        from.setHours(hours);
        from.setMinutes(minutes);
        if (to) {
            to.setHours(hours);
            to.setMinutes(minutes);
        }

        setRange({ from, to });
        onChange?.({ from, to });
    };

    const updateTime = (hours: number, minutes: number ) => {
        setHours(hours);
        setMinutes(minutes);

        const from = range.from ? new Date(range.from) : undefined;
        const to = range.to ? new Date(range.to) : undefined;

        if (from) from.setHours(hours, minutes);
        if (to) to.setHours(hours, minutes);

        setRange({ from, to });
        onChange?.({ from, to });
    };

    const formatRange = () => {
        if (!range.from) {
            return "Select a date."
        }

        const fromToString = format(range.from, doesGetTime ? "PPP p" : "PPP");

        if (!range.to) {
            return fromToString;
        }

        const toToString = format(range.to, doesGetTime ? "PPP p" : "PPP");

        return `${fromToString} → ${toToString}`;

    }

    return (
        <div className={cn("flex flex-col space-y-1", className)}>
            {label && <span className="text-xs text-muted-foreground">{label}</span>}
            <Popover open={open} onOpenChange={setOpen}>
                <PopoverTrigger asChild>
                    <Button variant="outline" className="justify-start text-left w-full">
                        <p className="mr-2 h-4 w-4">🗓️</p>
                        {formatRange()}
                    </Button>
                </PopoverTrigger>
                <PopoverContent className="w-auto p-4 space-y-4">
                    <Calendar
                        mode="range"
                        selected={range}
                        onSelect={(newRange) => newRange && updateDateRange(newRange)}
                        className="rounded-md border"
                    />
                    {doesGetTime && (
                        <div className="flex items-center gap-2">
                            <Input
                                type="number"
                                min={0}
                                max={23}
                                value={hours}
                                onChange={(e) => updateTime(Number(e.target.value), minutes)}
                                className="w-16"
                            />
                            <span>:</span>
                            <Input
                                type="number"
                                min={0}
                                max={59}
                                value={minutes}
                                onChange={(e) => updateTime(hours, Number(e.target.value))}
                                className="w-16"
                            />
                        </div>
                    )}
                </PopoverContent>
            </Popover>
        </div>
    );
};