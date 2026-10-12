export function objectsDeeplyEqual(cmp1,cmp2){
    if (cmp1 == cmp2) return true ;

    if ( cmp1=== null || cmp2=== null ) return false ;

    if (typeof cmp1 !== "object" || typeof cmp2 !== "object" ) return false ;

    const keys1 = Object.keys(cmp1); // Object.keys() returns an array containing an object's own enumerable property names
    const keys2 = Object.keys(cmp2);

    // Check whether both objects have the same number of properties

    if (keys1.length !== keys2.length) return false ;

    // loop through the property names

    for (let i=0;i<keys1.length;i++){
        const key = keys1[i];
        //Check that the second object has the property found in the first
        if (!Object.prototype.hasOwnProperty.call(cmp2,key)) return false ;
        //Compare the property values recursively
        if (!objectsDeeplyEqual(cmp1[key],cmp2[key])) return false ;
    }

    return true ;
}
