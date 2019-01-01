#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char* encode(char*);
char* decode(char*);

int main()
{
    char* s = "AAAAABBBCCCDAAV";
    printf("%s\n", encode(s));
    printf("%s\n", decode(encode(s)));

}

char* encode(char* string) {
    //variables
    int initialSeq = 0;
    char* encodedString = (char*)malloc(sizeof(char)*(strlen(string)*2));

    //string cleaning
    memset(encodedString, 0, strlen(string));

    //encoding loop
    for(int i=0; i<strlen(string); i++) {
        //current char differs from the char of the sequence
        if(string[initialSeq] != string[i]) {
            //print into the string the occurrence number and the character
            sprintf(encodedString + strlen(encodedString), "%d%c", (i - initialSeq), string[i-1]);
            //new sequence starts at i
            initialSeq = i;
        }
    }

    //adding last character
    sprintf(encodedString+strlen(encodedString), "%d%c", strlen(string) - initialSeq, string[strlen(string)-1]);

    //return the string
    return encodedString;

}

char* decode(char* string) {

    //variable
    int multiplierCounter = 0;
    //multiplier track the number of the occurrence found (i.e: 45A -> 45 times A:
    char multiplier[8];
    char* decodedString = (char*)malloc(sizeof(char)*strlen(string));

    //string cleaning
    memset(multiplier, 0, sizeof(multiplier));
    memset(decodedString, 0, sizeof(string));

    //decoding loop
    for(int i=0; i<strlen(string); i++) {
        //if the current char is a digit (0-9)
        if((string[i] - 0) < 65) {
            //add to the multiplier
            multiplier[multiplierCounter++] = string[i];

            //if next char occurrence isn't a number, time to translate
            if(!((string[i+1] - 0) < 65)) {
                //for the lenght of the repeated time number of the letter
                for(int j=0; j<atoi(multiplier); j++) {
                    //print into the buffer the char
                    sprintf(decodedString + strlen(decodedString), "%c", string[i+1]);
                }
                 
                //clean string and index
                memset(multiplier, 0, strlen(multiplier));
                multiplierCounter = 0;
            }
        }
    }


    return decodedString;
}
