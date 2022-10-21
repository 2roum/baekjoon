def solution(money):
    dp = []
    for i in range(len(money)):
        dp.append([0,0])
    
    length = len(money)
    dp[length-1][0] = money[length-1]
    for i in range(length - 1, 1, -1):
        dp[i-1][0] = max(money[i-1] + dp[i][1], dp[i][0])
        dp[i-1][1] = dp[i][0]
    temp = max(dp[1][0], dp[1][1])
    dp[length-1][0] = 0
    for i in range(length - 1, 0, -1):
        dp[i-1][0] = max(money[i-1] + dp[i][1], dp[i][0])
        dp[i-1][1] = dp[i][0]
    return max(temp, dp[0][0], dp[0][1])

if __name__ == '__main__':
    input = [1,2,3,1]
    print(solution(input))
    
