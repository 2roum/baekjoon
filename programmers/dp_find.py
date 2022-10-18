mod = 1000000007
def solution(m, n, puddles):
    answer = 0
    dp = []
    for i in range(m):
        dp.append([0] * n)
    puddles = [[x[0]-1, x[1]-1] for x in puddles]
    print(puddles)
    return solve_dp(dp, puddles, 0, 0, m, n)

def solve_dp(dp, puddles, i, j, m, n):
    if i == m-1 and j == n-1:
        return 1
    if i >= m or j >= n:
        return 0
    if dp[i][j] != 0:
        return dp[i][j]        
    if [i+1, j] not in puddles:
        dp[i][j] += solve_dp(dp, puddles, i+1, j, m, n)
    if [i, j+1] not in puddles:
        dp[i][j] += solve_dp(dp, puddles, i, j+1, m, n)
    dp[i][j] %= mod
    return dp[i][j]

if __name__ =='__main__':
    print(solution(4, 3, [[2,2]]))