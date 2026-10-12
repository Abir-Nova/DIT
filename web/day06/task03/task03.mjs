export function  countGs(str){
    let count = 0;
    for (let i=0;i<str.length;i++){
        if (str[i].toLowerCase() ==='g') {
            count++;
        }
    }
    return count;
}