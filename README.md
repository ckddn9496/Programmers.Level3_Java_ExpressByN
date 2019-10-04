# Programmers.Level3_Java_ExpressByN

## 프로그래머스 > 코딩테스트 연습 > 동적계획법(DP) > N으로 표현

### 1. 문제설명

문제: https://programmers.co.kr/learn/courses/30/lessons/42895

input으로  사용할 수 있는 자연수 `N (1 ~ 9)`, 만들고자 하는 숫자 `number`가 들어온다. 숫자`N`과 사칙연산만들 이용하여 `number`를 표현하려 할때, N의 사용횟수의 최솟값을 return하는 문제. 최대 8번까지 `N`을 사용가능하여 그보다 많은 `N`을 이용해야 한다면 -1을 return한다.

ex. `N = 5` 일때 `N` 을 두번 사용

  > 55, 10 (5+5), 0 (5-5), 25 (5\*5), 1 (5/5)

이 만들어 질 수 있다.

### 2. 풀이

처음 DP문제를 푸려고 했다가 이문제 때문에 아래 문제를 먼저 풀고 돌아왔다. 많이 어렵고 막막했지만 DP임을 생각하며 `N`을 `k`번 사용하여 만들어 지는 숫자를 찾기 위해서 `N`을 `i (1<i<k)`번 사용한 숫자와 `j (= k-i)`번 사용한 숫자의 조합으로 찾을 수 있다 생각하였다.

자료구조로는 ArrayList 배열을 이용하며, 배열의 index는 사용한 `N`의 횟수를 뜻한다. 초기화로 `K`번 `N`을 이어 붙여 만들어 질 수 있는 숫자를 추가한다. 0번째 index는 사용하지 않으며 1번째 index는 오직 `N`만이 원소로 들어가며 이후 index를 채워나간다. 만드는 중간에 number와 일치하는 숫자가 만들어 지면 횟수를 return한다.

```java
for (int i = 1; i < 9; i++) {
  if (NN == number) {
    return i;
  }
  numbersByUsedTimes[i] = new ArrayList<>();
  numbersByUsedTimes[i].add(NN);
  NN = NN*10 + N;
}
```

이후 k번 사용한 숫자를 만든다. `N`을 k번 사용한 숫자는 i번 사용한 숫자들과 j번 사용한 숫자 `(i, j < k 이며 k = i + j` 들의 조합으로 만들어 질수있다. 이전에 사용한 계산을 다시 하지않고 재사용하기에 이런 이유로 DP이다. 사칙연산을 수행하여 `N` k번 사용하여 만들어 질 수 있는 모든 숫자를 계산하면 그 중 number와 일치하는 숫자가 만들어 졌는지 확인한다. 만약 존재한다면 k를 return한다. 이렇게 k=8까지 반복하였지만 number와 일치하는 숫자가 만들어 지지 않았다면, 그 이상 사용해야만 하므로 -1을 return하여 해결하였다.

```java
for (int times = 2; times < 9; times++) {
  for (int i = 1; i < times; i++) {
    int j = times - i;
    for (int left : numbersByUsedTimes[i]) {
      for (int right : numbersByUsedTimes[j]) {
        // add
        numbersByUsedTimes[times].add(left+right);
        // sub
        numbersByUsedTimes[times].add(left-right);
        // mul
        numbersByUsedTimes[times].add(left*right);
        // div
        if (right != 0)
          numbersByUsedTimes[times].add(left/right);
        else
          numbersByUsedTimes[times].add(0);
      }
    }

    if (numbersByUsedTimes[times].contains(number))
      return times;

  }
}

return -1;
```
