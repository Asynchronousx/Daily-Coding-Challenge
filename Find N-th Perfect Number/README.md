Problem asked by Microsoft at their interview.

A solution to the problem of given a positive integer n, return the n-th perfect number.<br>
For example, given 1, we should return 19. Given 2, we should return 28 etc.<br>

The problem was solved in a single line using a costant time approach (O(1)).<br>

The approach used was to find the trend of changing of the 10th element of each subsolution:<br>
for example, the **9th** perfect number is *91*, but the **10th** is *109*.<br>
The 19th perfect number is *181*, but the **20th** is 208.<br>
The **29th** is 271 but the **30th** is *307*.<br>

Discovering the series behind the logic, we find that there is a multiplier to increase dinamically the sum and product of the perfect numbers.<br>
The solution is: First Perfect Number + 9*Nth-1 + The series Multiplier, where the multiplier is given by: <br>
1th: 9*1, 2th 9*2, 3th 9*3 etc.
