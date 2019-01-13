This problem was asked by Amazon at their interview.

A solution to the problem of: Given an integer k and a string s, find the length of the longest substring that contains 
at most k distinct characters.
For example, given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".

This problem run in O(N*M) and costant space, where N is the lenght of the string passed to the function,
and M is the lenght of the current substring analyzed when the character count reach K.
We got then a worst and a best case: The worst case is when the input string is composed by N equal character:
e.g: "aaaaaaaaa", then the complexity will be O(N^2).T
The best case is when the string is composed by N different character: "abcdefghi", then O(N).
The middle case is our target, and it will be composed by N character as: "aabacbebebe", with a time complexity of O(N*M).

A solution in O(N) using character windows is coming.
