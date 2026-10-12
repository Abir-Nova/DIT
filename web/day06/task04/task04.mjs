export function fizzBuzz(num) {
    let result =[];
    for (let i = 1; i <= num; i++) {
        if (i % 5 === 0 && i % 3 === 0) result.push("FizzBuzz")
        else if (i % 3 === 0) result.push("Fizz")
        else if (i % 5 === 0) console.log("Buzz")  
        else result.push(i);
    }
    console.log(result.join(", "));
}