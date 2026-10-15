<?php

    // 1 
    // 11 
    // 21
    // 1211
    // 111221
    // 312211
    // 13112221
    // 1113213211
    function sequence(int $n):void{
        
        if ($n<0) return;
        // Iteration 0 always starts with the string "1"
        // Keep it as a string because we are building digit sequences
        $current = "1";
        for( $i=0 ; $i <= $n ; $i++){
            // Print the current term followed by a newline
            echo $current . PHP_EOL;

            // if this is the final requested iteration -> there is no need to calculate another term
            if ( $i == $n ) break;

            // Prepare an empty string for the next term
            $next = "";

            // Find the number of digits in the current term
            $length = strlen($current);

            // Position of the digit we are currently examining
            $j = 0;

            // continue until we have examined every digit

            while ($j < $length){
                //remember the digit at the current position
                $digit=$current[$j];

                // We have found at least one occurrence of this digit
                $count = 1;

                // Count any identical digits immediately following it -> Stop when the digit changes or we reach the end 
                while(($j + $count < $length)  && ($current[$j + $count] == $digit)){
                    $count++;                
                }

                // append the count first then the digit
                // Example: two consecutive "1" digits become "21
                $next .= $count . $digit;

                //skip the group we have just counted 

                $j += $count;
            }

            // the new term becomes the current term for the next loop
            $current = $next; 

        }
    }
?>

