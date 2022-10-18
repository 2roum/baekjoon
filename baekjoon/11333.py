dp = []
mod = 1000000007
def find(n , opt, max):
    if n == max:
        return 1
    if n > max:
        return 0
    if dp[n][opt] != 0:
        return dp[n][opt]
    dp[n][opt] = (find(n+3, 0, max) + find(n+3, 1, max) + find(n+3, 2, max)) % mod
    return dp[n][opt]
    

if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        n = int(input())
        dp = [[0]*3 for _ in range(n)]
        ans = (find(0,0,n) + find(0,1,n) + find(0,2,n)) % mod
        print(ans)
        