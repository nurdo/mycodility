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

Suppose a 4-element slice `(i, i+3)`. It has five subslices, including three 2-element subslices, `(i, i+1)`, `(i+1, i+2)` and `(i+2, i+3)`, and two 3-element subslices, `(i, i+2)` and `(i+1, i+3)`. We have 

	average of (i, i+1) = (A[i] + A[i+1]) / 2	(1)
	average of (i+1, i+2) = (A[i+1] + A[i+2]) / 2	(2)
	average of (i+2, i+3) = (A[i+2] + A[i+3]) / 2	(3)
	average of (i, i+2) = (A[i] + A[i+1] + A[i+2]) / 3	(4)
	average of (i+1, i+3) = (A[i+1] + A[i+2] + A[i+3]) / 3	(5)
	average of (i, i+3) = (A[i] + A[i+1] + A[i+2] + A[i+3]) / 4	(6)

An example slice of four elements which satisfies the below conditions is `[4, 5, 6, 3]`. 

    average of (i, i+3) <= average of (i, i+1)	(7)
    average of (i, i+3) <= average of (i+1, i+2)	(8)
    average of (i, i+3) <= average of (i+2, i+3)	(9)
    average of (i, i+3) <= average of (i, i+2)	(10)
    average of (i, i+3) <= average of (i+1, i+3)	(11)
    
From (6), we have

    average of (i, i+3) = (A[i] + A[i+1] + A[i+2] + A[i+3]) / 4	(6)
                        = ((A[i] + A[i+1]) / 2 + (A[i+2] + A[i+3]) / 2) / 2 
                        = (average of (i, i+1) + average of (i+2, i+3)) / 2 	For (1) and (3)
                        <= (average of (i, i+3) + average of (i, i+3)) / 2
               
The above is valid only when `average of (i, i+3) = average of (i, i+1)` (12) and `average of (i, i+3) = average of (i+2, i+3)` (13). Therefore, we obtain that the minimal average of a 4-element slice can not be smaller than the minimal averages of its subslices.

### For slices of 5 integers

Suppose a 5-element slice `(i, i+4)`. It has nine subslices, including four 2-element subslices, `(i, i+1)`, `(i+1, i+2)`, `(i+2, i+3)` and `(i+3, i+4)`, three 3-element subslices, `(i, i+2)`, `(i+1, i+3)` and `(i+2, i+4)`, and two 4-element slices, `(i, i+3)` and `(i+1, i+4)`. We have

	average of (i, i+1) = (A[i] + A[i+1]) / 2	(14)
	average of (i+1, i+2) = (A[i+1] + A[i+2]) / 2	(15)
	average of (i+2, i+3) = (A[i+2] + A[i+3]) / 2	(16)
	average of (i+3, i+4) = (A[i+3] + A[i+4]) / 2	(17)
	average of (i, i+2) = (A[i] + A[i+1] + A[i+2]) / 3	(18)
	average of (i+1, i+3) = (A[i+1] + A[i+2] + A[i+3]) / 3	(19)
	average of (i+2, i+4) = (A[i+2] + A[i+3] + A[i+4]) / 3	(20)	
	average of (i, i+3) = (A[i] + A[i+1] + A[i+2] + A[i+3]) / 4	(21)
	average of (i+1, i+4) = (A[i+1] + A[i+2] + A[i+3] + A[i+4]) / 4	(22)
	average of (i, i+4) = (A[i] + A[i+1] + A[i+2] + A[i+3] + A[i+4]) / 5	(23)
	
and `(i, i+4)` has to satisfy 

    average of (i, i+4) <= average of (i, i+1)	(24)
    average of (i, i+4) <= average of (i+1, i+2)	(25)
    average of (i, i+4) <= average of (i+2, i+3)	(26)
    average of (i, i+4) <= average of (i+3, i+4)	(27)
    average of (i, i+4) <= average of (i, i+2)	(28)
    average of (i, i+4) <= average of (i+1, i+3)	(29)
    average of (i, i+4) <= average of (i+2, i+4)	(30)
    average of (i, i+4) <= average of (i, i+3)	(31)
    average of (i, i+4) <= average of (i+1, i+4)	(32)
    
From (23), we have
 
       average of (i, i+4) = (A[i] + A[i+1] + A[i+2] + A[i+3] + A[i+4]) / 5
    -> 5 * average of (i, i+4) = A[i] + A[i+1] + A[i+2] + A[i+3] + A[i+4]
                               = 2 * (A[i] + A[i+1]) / 2 + 3 * (A[i+2] + A[i+3] + A[i+4]) / 3
                               = 2 * average of (i, i+1) + 3 * average of (i+2, i+4)
                               >= 2 * average of (i, i+4) + 3 * average of (i, i+4)	For (24) and (30)
                               >= 5 * average of (i, i+4)
                               
The above is valid only when

    average of (i, i+4) = average of (i, i+1)	(33)
    average of (i, i+4) = average of (i+2, i+4)	(34)

Similarly, I can prove that

    average of (i, i+4) = average of (i, i+2)	(35)
    average of (i, i+4) = average of (i+3, i+4)	(36)

From (5), we have 
                           
    average of (i, i+2) = (A[i] + A[i+1] + A[i+2]) / 3
                        = (2 * (A[i] + A[i+1]) / 2 + A[i+3]) / 3
                        = (2 * average of (i, i+1) + A[i+3]) / 3
                        = (2 * average of (i, i+4) + A[i+3]) / 3	For (33)
                              
Combined with (28), i.e. `average of (i, i+4) = average of (i, i+2)`, we have 

       average of (i, i+4) = (2 * average of (i, i+4) + A[i+3]) / 3
    -> 3 * average of (i, i+4) = 2 * average of (i, i+4) + A[i+3]
    -> average of (i, i+4) = A[i+3]	(37)
    
Consequently, we obtain `A[i+4] = average of (i, i+4)` based on (36), and similarly `average of (i, i+4) = A[i] = A[i+1] = A[i+2]`	(38). 

### For slices of more than 5 integers

Suppose a `q+1`-element slice `(i, i+q)` where `q >= 5`. It is easy\* to prove that `(i, i+q)` can be composed by slices of 2 and 3 elements, and there always exists a 3-element slice of a composition which contains a 2-element slice of another composition. Therefore, a proof similar to the above can be established, and we obtain `A[i] = A[i+1] = ... = A[i+q]`. 

\* Suppose that `q` is an even number, i.e. `q = 2*n`. Then, `q+1 = 2*n + 1 = 2*(n-1) + 3`. If `q` is an odd number, i.e. `q = 2*n + 1`. Then, `q+1 = 2*n + 1 + 1 = 2*(n-2) + 3*2`. In the former case, slice `(i, i+q)` can be composed as `(i, i+1), (i+2, i+4), (i+5, i+6), ..., (i+q-1, i+q)` or `(i, i+2), (i+3, i+4), (i+5, i+6), ..., (i+q-1, i+q)`, where slice `(i, i+2)` of the second composition encloses slice `(i, i+1)` of the first. In the latter case, slice `(i, i+q)` can be composed as `(i, i+1), (i+2, i+4), (i+5, i+7), (i+8, i+9), ..., (i+q-1, i+q)` or `(i, i+2), (i+3, i+4), (i+5, i+7), (i+8, i+9), ..., (i+q-1, i+q)`,  where slice `(i, i+2)` of the second composition encloses slice `(i, i+1)` of the first. 

## Algorithm

Based on the above observations and proofs, my algorithm for the MinAvgTwoSlice problem is to examine the elements of array `A` from index `0` to `N-2` (`N` is the length of `A`). For every index, the averages of two slices, `(i, i+1)` and `(i, i+2)`, are compared (since slice `(i, i+3)` can not have a average lower than slices `(i, i+1)` and `(i, i+2)`), and the smaller is recorded as the minimal average of any slice starting at `i`. In addition, the condition `A[i] = A[i+1] = A[i+2] = A[i+3] = A[i+4]` is checked. If it is valid, my algorithm continues visiting in turn elements following `A[i+4]` and stops at the first element `A[j]` where `A[j] != A[j-1]` or `j=N-1`. In case `A[j] != A[j-1]`, indices `i+1, i+2, ..., j-3`\* are discarded from being considered as potential starting indices of slices with the minimal average of array `A`. If `A[i] = A[i+1] = A[i+2] = A[i+3] = A[i+4]` is not valid, index `i+1` is visited next, and the minimal average of a slice starting at `i+1` is to be found in a similar way and compared with the minimal average of a slice starting at `i`. After traversing `A`, my algorithm will find the starting index of the slice with the minimal average of `A`.

\* It is because the 3-element slice `(j-2, j)` could potentially have the minimal average. 

## Complexity

Prefix sums (as in https://codility.com/media/train/3-PrefixSums.pdf) are precomputed. 

My algorithm scans `A` once and does 6 (2 + 4, see above) comparisons when visiting each element. Therefore, the time complexity is `O(N)`.

An additional array is used to keep the prefix sums, hence the space complexity is `O(N)` as well. 

The test results of my algorithm provided by Codility are enclosed.
