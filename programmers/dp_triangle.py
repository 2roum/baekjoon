def solution(triangle):
    lst = []
    for i in range(len(triangle)):
        lst.append([0] * len(triangle))
    ans =  recur(0, 0, lst, triangle)  
    return ans

def recur(cnt, n, dp, triangle):
    if dp[cnt][n] != 0:
        return dp[cnt][n]
    if cnt == len(triangle) - 1:
        dp[cnt][n] = triangle[cnt][n]
        return dp[cnt][n]
    dp[cnt][n] = triangle[cnt][n] + max(recur(cnt + 1, n, dp, triangle), recur(cnt+1, n+1, dp, triangle))
    return dp[cnt][n]