public class MinAvgTwoSlice {
    
    static int solution(int[] A) {
        int N = A.length;
        
        if (N == 2) {
            return 0;
        }
        
        int[] sumA= new int[N+1];
        sumA[0] = 0;
        for (int i=0; i < N; i++) {
            sumA[i+1] = sumA[i] + A[i];
        }
        
        int currentInd = 0;
        int minInd = 0;
        double minAve = (double) sumA[2]/2;
        
        while (currentInd < N-1) {
            int i = 1;
            while (currentInd+i<N && i<4) {
                double ave = (double) (sumA[currentInd+i+1] - sumA[currentInd])/(i+1);
                if (ave < minAve) {
                    minAve = ave;
                    minInd = currentInd;
                }
                i++;
            }
            
            if (currentInd+4 < N && A[currentInd+4]==A[currentInd+3] && A[currentInd+3]==A[currentInd+2]
                && A[currentInd+2]==A[currentInd+1] && A[currentInd+1]==A[currentInd]) {
                currentInd += 5;
                while (currentInd<N && A[currentInd] == A[currentInd-1]) {
                    currentInd++;
                }
                currentInd -= 3;
            }
            else {
                currentInd++;
            }
        }
        
        return minInd;
    }
    
    public static void main(String[] args) {
        int i = solution(new int[] {4, 2, 2, 5, 1, 5, 8});
        System.out.print(i);
    }
    
}

