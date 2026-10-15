<?php

function calc(string $operator, int $nbr1,int $nbr2){
    if ( $operator != "+" && $operator != "-" && $operator != "/" && $operator != "%" &&  $operator != "*" )
        return "invalid operator";
    if (($operator=="/" || $operator=="%")&&($nbr2 == 0))
            return "can't divide by 0";
    if ($operator =="+")
        return $nbr1 + $nbr2;
    else if ($operator =="-")
        return $nbr1 - $nbr2;
    else if ($operator =="/")
        return $nbr1 / $nbr2;
    else if ($operator =="*")
        return $nbr1 * $nbr2;
    else
        return $nbr1 % $nbr2;

        
}

?>