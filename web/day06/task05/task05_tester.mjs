
import { range } from './task05.mjs';

// Test 1: Increasing range with step 2
console.log(range(1, 10, 2));

// Test 2: Increasing range with default step
console.log(range(19, 22));

// Test 3: Decreasing range with step -1
console.log(range(5, 2, -1));

// Test 4: Start and end are equal
console.log(range(3, 3));

// Test 5: Step moves in the wrong direction
console.log(range(1, 5, -1));
console.log(range(5, 1, 1));

// Test 6: Step is zero
console.log(range(1, 5, 0));
