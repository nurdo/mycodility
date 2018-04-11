The MinAvgTwoSlice problem of Codility is described here: [https://app.codility.com/programmers/lessons/5-prefix_sums/min_avg_two_slice/](https://app.codility.com/programmers/lessons/5-prefix_sums/min_avg_two_slice/).

My solution is based on the following observations (and their proofs). 

## Proof

A slice has at least two elements. For a slice of three elements to have the minimal average, it has to have a average equal or smaller than the average of any of its subslices (slices contained within itself). 

### For slices of 3 integers

Suppose a 3-element slice `(i, i+2)`. It has two subslices `(i, i+1)` and `(i+1, i+2)`. We have

	average of (i, i+1) = (A[i] + A[i+1]) / 2
	average of (i+1, i+2) = (A[i+1] + A[i+2]) / 2
    average of (i, i+2) = (A[i] + A[i+1] + A[i+2]) / 3
	
It is easy to find examples of 3-element slices, e.g., `[3, 7, 1]`, which satisfy the following conditions.
    
    average of (i, i+2) <= average of (i, i+1)
    average of (i, i+2) <= average of (i+1, i+2)
 	
### For slices of 4 integers

Suppose a 4-element slice `(i, i+3)`. It has five subslices, including three 2-element subslices, `(i, i+1)`, `(i+1, i+2)` and `(i+2, i+3)`, and two 3-element subslices, `(i, i+2)` and `(i+1, i+3)`. An example slice of four elements which satisfies the below conditions is `[4, 5, 6, 3]`. 

    average of (i, i+3) <= average of (i, i+1)
    average of (i, i+3) <= average of (i+1, i+2)
    average of (i, i+3) <= average of (i+2, i+3)
    average of (i, i+3) <= average of (i, i+2)
    average of (i, i+3) <= average of (i+1, i+3)

### For slices of 5 integers

Suppose a 5-element slice `(i, i+4)`. It has nine subslices, including four 2-element subslices, `(i, i+1)`, `(i+1, i+2)`, `(i+2, i+3)` and `(i+3, i+4)`, three 3-element subslices, `(i, i+2)`, `(i+1, i+3)` and `(i+2, i+4)`, and two 4-element slices, `(i, i+3)` and `(i+1, i+4)`. We have

	average of (i, i+1) = (A[i] + A[i+1]) / 2	(1)
	average of (i+1, i+2) = (A[i+1] + A[i+2]) / 2	(2)
	average of (i+2, i+3) = (A[i+2] + A[i+3]) / 2	(3)
	average of (i+3, i+4) = (A[i+3] + A[i+4]) / 2	(4)
	average of (i, i+2) = (A[i] + A[i+1] + A[i+2]) / 3	(5)
	average of (i+1, i+3) = (A[i+1] + A[i+2] + A[i+3]) / 3	(6)
	average of (i+2, i+4) = (A[i+2] + A[i+3] + A[i+4]) / 3	(7)	
	average of (i, i+3) = (A[i] + A[i+1] + A[i+2] + A[i+3]) / 4	(8)
	average of (i+1, i+4) = (A[i+1] + A[i+2] + A[i+3] + A[i+4]) / 4	(9)
	average of (i, i+4) = (A[i] + A[i+1] + A[i+2] + A[i+3] + A[i+4]) / 5	(10)
	
and `(i, i+4)` has to satisfy 

    average of (i, i+4) <= average of (i, i+1)	(11)
    average of (i, i+4) <= average of (i+1, i+2)	(12)
    average of (i, i+4) <= average of (i+2, i+3)	(13)
    average of (i, i+4) <= average of (i+3, i+4)	(14)
    average of (i, i+4) <= average of (i, i+2)	(15)
    average of (i, i+4) <= average of (i+1, i+3)	(16)
    average of (i, i+4) <= average of (i+2, i+4)	(17)
    average of (i, i+4) <= average of (i, i+3)	(18)
    average of (i, i+4) <= average of (i+1, i+4)	(19)
    
From (10), we have
 
       average of (i, i+4) = (A[i] + A[i+1] + A[i+2] + A[i+3] + A[i+4]) / 5
    -> 5 * average of (i, i+4) = A[i] + A[i+1] + A[i+2] + A[i+3] + A[i+4]
                               = 2 * (A[i] + A[i+1]) / 2 + 3 * (A[i+2] + A[i+3] + A[i+4]) / 3
                               = 2 * average of (i, i+1) + 3 * average of (i+2, i+4)
                               >= 2 * average of (i, i+4) + 3 * average of (i, i+4)	For (11) and (17)
                               >= 5 * average of (i, i+4)
                               
The above is only valid when

    average of (i, i+4) = average of (i, i+1)	(20)
    average of (i, i+4) = average of (i+2, i+4)	(21)

Similarly, I can prove that

    average of (i, i+4) = average of (i, i+2)	(22)
    average of (i, i+4) = average of (i+3, i+4)	(23)

From (5), we have 
                           
    average of (i, i+2) = (A[i] + A[i+1] + A[i+2]) / 3
                        = (2 * (A[i] + A[i+1]) / 2 + A[i+3]) / 3
                        = (2 * average of (i, i+1) + A[i+3]) / 3
                        = (2 * average of (i, i+4) + A[i+3]) / 3	For (20)
                              
Combined with (22), i.e. `average of (i, i+4) = average of (i, i+2)`, we have 

       average of (i, i+4) = (2 * average of (i, i+4) + A[i+3]) / 3
    -> 3 * average of (i, i+4) = 2 * average of (i, i+4) + A[i+3]
    -> average of (i, i+4) = A[i+3]	(24)

Similarly, I can prove that `average of (i, i+4) = A[i] = A[i+1] = A[i+2] = A[i+3] = A[i+4]`	(25). 

### For slices of more than 5 integers

Suppose a (q+1)-element slice `(i, i+q)` where `q >= 5`. For an arbitrary index `m` in `(i, i+q)`, there is at least a slice `(j, k)` where `j<=m<=k`. Similar to the above, we have `A[j] = A[j+1] = ... = A[k]`. Since `m` is arbitrary, we have `A[i] = A[i+1] = ... = A[i+q]`. 

## Algorithm

Based on the above observations and proofs, my algorithm for the MinAvgTwoSlice problem is to examine the elements of array `A` from index `0` to `N-2` (`N` is the length of `A`). For every index, the averages of three slices, `(i, i+1)`, `(i, i+2)` and `(i, i+3)`, are compared and the smallest is recorded as the minimal average of any slice starting at `i`. In addition, the condition `A[i] = A[i+1] = A[i+2] = A[i+3] = A[i+4]` is checked. If it is valid, my algorithm continues visiting one after another elements following `A[i+4]` and stop at the first element `A[j]` where `A[j] != A[j-1]`, and indices `i+1, i+2, ..., j-4`* are discarded from being considered as potential starting indices of slices with the minimal average of array `A`. If `A[i] = A[i+1] = A[i+2] = A[i+3] = A[i+4]` is not valid, index `i+1` is visited next, and the minimal average of a slice starting at `i+1` is to be found in a similar way and compared to the minimal average of a slice starting at `i`. After traversing `A`, this algorithm will find the starting index of the slice with the minimal average.

\* It is because the 4-element slice `(j-3, j)` could potentially have the minimal average. 

## Complexity

Prefix sums (as in [https://codility.com/media/train/3-PrefixSums.pdf]) are precomputed. 

My algorithm scans `A` once and does 7 (3 + 4, see above) comparisons when visiting each element. Therefore, the time complexity is `O(N)`.

An additional array is used to keep the prefix sums, hence the space complexity is `O(N)` as well. 

The test results of my algorithm provided by Codility is enclosed.

