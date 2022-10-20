MAX = 1000000

def get_Nnum(N, cnt):
    ret = 0
    while cnt != 0:
        cnt-=1
        ret = ret * 10 + N
    return ret

def solution(N, number):
    answer = 0
    dp = []
    dp.append({})
    dp.append({N})
    for k in range(2,9):
        temp_set = {get_Nnum(N, k)} 
        for i in range(1,k):
            for j in range(1,k): 
                if i + j == k:
                    for a in dp[i]:
                        for b in dp[j]:
                            temp_set.add(a + b)
                            if a >= b:
                                temp_set.add(a-b)
                            temp_set.add(a * b)
                            if b != 0 :
                                temp_set.add(a // b)
        dp.append(temp_set)   
    cnt = 0                       
    for temp_set in dp:
        if number in temp_set:
            return cnt
        cnt+=1
    return -1

if __name__ == '__main__':
    print(solution(2, 11))
