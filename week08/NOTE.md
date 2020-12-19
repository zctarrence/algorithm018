## 学习笔记<br>
(感谢老师的点评，老师辛苦啦！）<br>

### 本周感想：
本周学习的内容较前几周难度有所降低，可以较好的接受。这周的话，很多时间还是放在了第七周与第六周的任务中。还需要抓紧，把前几周没做的题补上来。

### 位运算的一些小tips
1s为一串1，0s为一串0.<br>
①
```
X ^ 1s = ~X  //可用来翻转
X ^ 0s = X 
X ^ X = 0 
```

②
```
X & 0s = 0
X & 1s = X
X & X = X
//实现掩码的操作，对特定位进行处理
```


③
```
X | 0s = X
X | 1s = 1s
X | X = X
//实现特定位的赋值
```

④ 位运算
```
n & (n - 1) //去除n中最低位的1，例01011000：
01011000 &
01010100
---------
01010000
```

```
n & (-n) //得到n的位级中最低的那一位1，例01011000：
01011000 &
10101000 
---------
00001000
```

⑤取特定的值
```
//取到第i位为1的值，例如 1 << (3 - 1)，得到第三位为1：0000 0100
1 << (i - 1) 
```

```
//取到第1到第i位为0的值，例如 (1 << 3) - 1，得到第1到3位为1的值：0000 0111
(1 << i) - 1
```

⑥java中的快速位操作
```
static int Integer.bitCount();           // 统计 1 的数量
static int Integer.highestOneBit();      // 获得最高位
static String toBinaryString(int i);     // 转换为二进制表示的字符串
```

### 部分初级排序手写代码：
①冒泡排序：
```
public static int[] function_bubbleSort(int[] arr) {
		int len = arr.length;
		for (int i  = 0; i < len; i++) {
			for (int j = 0; j < len - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		return arr;
	}
```

②选择排序：<br>
version1
```
public static int[] function_selectionSort(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < len; j++) {
				if (arr[minIndex] > arr[j]) {
					minIndex = j;
				}			    
			}
			int temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
		return arr;
	}
  ```
  <br>
version2
```
public static int[] function_selectionSort(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}			    
			}
		}
		return arr;
	}
  ```
  
③插入排序：
```
public static int[] function_insertionSort(int[] arr) {
		int len = arr.length;
		for (int i = 1; i < len; i++) {
			int preIndex = i - 1;
			int curr = arr[i];
			while (preIndex >= 0 && curr < arr[preIndex]) {
				arr[preIndex + 1] = arr[preIndex];
				preIndex--;
			}
			arr[preIndex + 1] = curr;
		}
		return arr;
	}
  ```
 
④希尔排序：
```
public static int[] function_shellSort(int[] arr) {
		int len = arr.length;
		for (int gap = len / 2; gap > 0; gap = gap / 2) {
			for (int i = gap; i < len; i++) {
				int j = i;
				int curr = arr[i];
				while (j - gap >= 0 && curr < arr[j - gap]) {
					arr[j] = arr[j - gap];
					j = j - gap;
				}
				arr[j] = curr;
			}
		}
		return arr;
	}
  ```

⑤归并排序（来自课程）：
```
public static void mergeSort(int[] array, int left, int right) {
		if (right <= left)
			return;
		int mid = (left + right) >> 1;

		mergeSort(array, left, mid);
		mergeSort(array, mid + 1, right);
		merge(array, left, mid, right);
	}

	public static void merge(int[] arr, int left, int mid, int right) {
		int[] temp = new int[right - left + 1];
		int i = left, j = mid + 1, k = 0;

		while (i <= mid && j <= right) {
			temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
		}

		while (i <= mid)
			temp[k++] = arr[i++];
		while (j <= right)
			temp[k++] = arr[j++];

		for (int p = 0; p < temp.length; p++) {
			arr[left + p] = temp[p];
		}
	}
  ```
⑥快排(来自课堂):
```
public static void quickSort(int[] array, int begin, int end) {
		if (end <= begin)
			return;
		int pivot = partition(array, begin, end);
		quickSort(array, begin, pivot - 1);
		quickSort(array, pivot + 1, end);
	}

	public static int partition(int[] a, int begin, int end) {
		int pivot = end, counter = begin;
		for (int i = begin; i < end; i++) {
			if (a[i] < a[pivot]) {
				int temp = a[counter];
				a[counter] = a[i];
				a[i] = temp;
				counter++;
			}
		}
		int temp = a[pivot];
		a[pivot] = a[counter];
		a[counter] = temp;
		return counter;
	}
  ```
 ⑦堆排序
 
 ⑧计数排序（基于leetcode 1122）：<br>
 步骤：<br>
 Ⅰ确认待排序数组的最大和最小元素 <br>
 Ⅱ以待排序数组的元素为下标，元素出现的个数为值，存入一个新的数组C <br>
 Ⅲ对数组C从头开始遍历，若C[i] > 0，则将i值填充入目标数组，并且C[i]值减一 <br>
 ```
 public static int[] function_countingSort(int[] arr, int min, int max) {
		int[] C = new int[max - min + 1];
		for (int i : arr) {
			C[i - min]++;
		}
		int index = 0;
		for (int i = 0; i < C.length; i++) {
			while (C[i] > 0) {
				arr[index++] = i + min;
				C[i]--;
			}
		} 
		return arr;
	}
  ```
⑨桶排序（思路基于leetcode 164）：<br>
步骤：（假设待排序数组为arr）<br>
 Ⅰ设计一个数组当空桶，数组长度要不小于arr.length - 1。（确保单个桶内的最大间隙小于桶间的最大距离） <br>
 Ⅱ把arr中的元素放入桶中 <br>
 Ⅲ比较不为空的桶间的距离，取最大值 <br>
```
class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int max = -1;
        int min = Integer.MAX_VALUE;
        //取最大和最小值
        for (int i : nums) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        //构造桶
        int[] bucketMax = new int[nums.length - 1];
        int[] bucketMin = new int[nums.length - 1];
        Arrays.fill(bucketMax, -1);
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
                
        //将数组nums中的元素入桶，计算桶内数据的最大值和最小值
        int interval = (int)(Math.ceil((double)(max - min) / (nums.length - 1)));
        for (int i : nums) {
            if (i == max || i == min) continue;
            int index = (i - min) / interval;
            bucketMax[index] = Math.max(bucketMax[index], i);
            bucketMin[index] = Math.min(bucketMin[index], i);
        }
        
        //得到间距
        int mapGap = 0;
        int prev = min;
        for (int i = 0; i < nums.length - 1; i++) {
            if (bucketMax[i] == -1) continue;
            mapGap = Math.max(bucketMin[i] - prev, mapGap);
            prev = bucketMax[i];
        }
        mapGap = Math.max(mapGap, max - prev);
        return mapGap;
    }
}
```
⑩基数排序<br>
堆排序和基数排序还没完成。

<br>
❤感谢老师的批阅❤
