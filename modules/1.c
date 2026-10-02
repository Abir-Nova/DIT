#include <stdio.h>
#include <stdbool.h>

/*
 * Check if n is between low_bound and high_bound.
 * Returns true if it is between them,
 * otherwise prints the error message and returns false.
 */
bool is_between(unsigned int n,
                unsigned int low_bound,
                unsigned int high_bound,
                const char *fail_message)
{
    if (n >= low_bound && n <= high_bound)
    {
        return true;
    }

    printf("%s\n", fail_message);
    return false;
}

int main(void)
{
    printf("[%s] %s: %d\n",
           "integer",
           "number",
           42);

    printf("%s",
           "Writing multiline statements in C is easy,"
           " you just need to break the line, and you are done!\n");

    if (
        true
        &&
        (
            true
            ||
            false
        )
        &&
        true
    )
    {
        printf("I'm a teapot! Code: %d\n", 418);
    }

    return 0;
}