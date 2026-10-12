
import { objectsDeeplyEqual } from './task07.mjs';

const obj = { here: { is: "an" }, object: 2 };

console.log(objectsDeeplyEqual(obj, obj));

console.log(objectsDeeplyEqual(obj, {
    here: 1,
    object: 2
}));

console.log(objectsDeeplyEqual(obj, {
    here: { is: "an" },
    object: 2
}));

// Additional tests
console.log(objectsDeeplyEqual(5, 5));
console.log(objectsDeeplyEqual({ a: 1 }, { a: 2 }));
console.log(objectsDeeplyEqual(null, { a: 1 }));
console.log(objectsDeeplyEqual(null, null));
console.log(objectsDeeplyEqual({ a: 1 }, { a: 1, b: 2 }));
