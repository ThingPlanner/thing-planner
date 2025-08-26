export function isValidHour(value: string): boolean {
    return /^(0[0-9]|1[0-9]|2[0-3])$/.test(value);
}

export function isValid12Hour(value: string): boolean {
    return /^(0[1-9]|1[0-2])$/.test(value);
}

export function isValidMinuteOrSecond(value: string): boolean {
    return /^[0-5][0-9]$/.test(value);
}

export function isValidEmail(email: string): boolean {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}