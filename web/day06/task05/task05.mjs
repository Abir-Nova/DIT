export function range(start, end, step) {
    if (step == undefined) {
        if (start < end) step = 1;
        else step = -1
    }
    let numbers = [];

    if (step === 0) return numbers;

    if (step > 0) {
        for (let i = start; i <= end; i += step) {
            numbers.push(i);
        }
    }
    else {
        for (let i = start; i >= end; i += step) {
            numbers.push(i);
        }
    }
    return numbers;
}