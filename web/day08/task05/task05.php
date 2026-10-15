<?php

    function calc_average(array $numbers){
        $sum = 0;
        $count =0;
        foreach($numbers as $number){
            $sum+=$number;
            $count++;
        }
        $average= $sum / $count;
        return round($average,1);
    }
    //1 : 1 tenth means 1 digit after the decimal point 
?>

