package org.alexuh.questions.leecode;


import java.util.*;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/27
 *
 * 递归回溯问题（也就是暴力搜索）
 *
 * 1.组合问题
 * 2.排列问题
 * 3.切割问题
 * 4.子集问题
 * 5.棋盘问题（n皇后 / 解数独）
 *
 */
public class BackTrackQuestions {

    public static void main(String[] args) {
        // new BackTrackQuestions().combine(4, 2);
        // new BackTrackQuestions().letterCombinations("23");
        //new BackTrackQuestions().combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
        //new BackTrackQuestions().permute(new int[]{1,2,3});
       // new BackTrackQuestions().permuteUnique(new int[]{1,1,2});
        new BackTrackQuestions().solveNQueens(16);
    }


    /**
     * 77. 组合
     *
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     *
     * 解法1：暴力破解多层for循环
     *
     * 解法2:递归+回溯类似于for循环，递归通过递归中止条件控制for循环层数
     * 假设为n=4 k=2暴力破解需要2层for循环，通过给定list集合收集结果，当list里的size为2时得到一个解并回溯到上一层
     *
     * @param n
     * @param k
     * @return
     */

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        // n从1开始所以index初始化值为1
        combineBackTrack(n, k , new ArrayList<>(), 1, result);
        System.out.println(result.get(0));
        return result;
    }

    /**
     *
     * @param n
     * @param k
     * @param list  临时存放结果容器
     * @param index 从哪开始的索引
     * @param result 收集结果集
     */
    public void combineBackTrack(int n, int k, List<Integer> list, int index, List<List<Integer>> result) {
        // 通过list里的数量控制for循环层数
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i <= n; i++) {
            list.add(i);
            // 递归进入下次循环相当于
            combineBackTrack(n, k , list, i+1, result);
            // 回溯到上一层 如[1,3] -> [1]
            list.remove(list.size()-1);
        }
    }

    /**
     * 216. 组合总和 III
     * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
     *
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSum3Helper(k, n, new ArrayList<>(), result, 1);
        return result;
    }

    public void combinationSum3Helper(int k, int n, List<Integer> list, List<List<Integer>> result, int index) {
        if (list.size() == k && getSum(list) != n) {
            return;
        }
        if (list.size() == k && getSum(list) == n) {
            result.add(new ArrayList<>(list));
            return;
        }
        for(int i = index; i <= 9; i++) {
            list.add(i);
            combinationSum3Helper(k, n, list, result, i+1);
            list.remove(list.size() - 1); // 回溯
        }
    }

    public int  getSum(List<Integer> list) {
        int sum = 0;
        for (int v : list) {
            sum += v;
        }
        return sum;
    }

    /**
     * 17. 电话号码的字母组合
     *给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     *给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。具体看leecode17
     * 2 [abc]
     * 3 [def] ...
     *
     *解法：递归 + 回溯
     *
     * 1.for遍历层数是给的数字个数
     * 2.for循环要遍历的值为abc def等映射集合
     * 3.需要收集的结果为ab,ae等string值
     *
     * 假设为23
     *      a              b          c
     *    d e f         d e f       d e f
     * ad ae af        bd be bf    cd ce  cf   结果集
     *
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        Map<Character, String[]> maps = new HashMap<>();
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        maps.put('2', new String[]{"a", "b", "c"});
        maps.put('3', new String[]{"d", "e", "f"});
        maps.put('4', new String[]{"g", "h", "i"});
        maps.put('5', new String[]{"j", "k", "l"});
        maps.put('6', new String[]{"m", "n", "o"});
        maps.put('7', new String[]{"p", "q", "r","s"});
        maps.put('8', new String[]{"t", "u", "v"});
        maps.put('9', new String[]{"w", "x", "y","z"});
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        letterCombinationsBackTrack(digits.toCharArray(),0, maps, sb, result);  // index从0开始也就是数字第一个（digits的第一个数字）
        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    /**
     *
     *
     * @param digits  给定的数字组合
     * @param index   当前是第几个数字
     * @param maps   数字和字母映射
     * @return
     */
    public void letterCombinationsBackTrack(char[] digits, int index, Map<Character, String[]> maps, StringBuilder sb, List<String> result) {
        // 递归结束条件,遍历到最后一个数字收集前面结果
        if (index  == digits.length) {
            result.add(sb.toString());
            return;
        }
        String[] dig = maps.get(digits[index]); // 数字映射集合 abc def
        // 递归 + 回溯
        for (int i = 0; i < dig.length; i++) {
            sb.append(dig[i]);
            letterCombinationsBackTrack(digits, index+1, maps,sb,result);   // 递归
            sb.deleteCharAt(sb.length() - 1);  // 回溯
        }
    }


    /**
     *39. 组合总和
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     *
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     *
     * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int sum = 0;
        combinationSumTrackBacking(candidates, target, new ArrayList<>(), sum, result,0);
        return result;
    }

    /**
     * 39. 组合总和
     *
     * 解法：递归+回溯（穷举）
     *
     * @param candidates
     * @param target
     * @param list 当前组合中的元素
     * @param sum 当前总和
     * @param result 当前结果集
     * @param index 当前所在数组位置
     * @return
     */
    public void combinationSumTrackBacking(int[] candidates, int target,List<Integer> list, int sum, List<List<Integer>> result, int index) {
        // 递归终止条件,如果大于目标值则回溯重新找，等于就收集结果并回溯
        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new ArrayList<>(list));
            return;
        }
        // 递归+回溯 使用index如0搜索0后面的 1搜素1后面的 防止有重复组合
        for(int i = index; i < candidates.length; i++) {
            list.add(candidates[i]);
            sum += candidates[i];
            combinationSumTrackBacking(candidates, target, list, sum, result,i); // 递归
            // 同时回溯sum和list
            sum -= list.get(list.size() - 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 40. 组合总和 II
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int sum = 0;
        //为了将重复的数字都放到一起，所以先进行排序,(和字符串)
        Arrays.sort(candidates);
        System.out.println(Arrays.toString(candidates));
        combinationSum2BackTrack(candidates, target, 0, sum, new ArrayList<>(), result);
        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    /**
     *  40. 组合总和 II
     *
     *  给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的每个数字在每个组合中只能使用 一次 。
     *
     * notice: 1.每个数字在c组合中只能使用一次所以递归index+1
     *         2.c中可能有重复元素所以要去掉同一层树上重复的元素 也就是  [ 1 2 2 2 3] 跳过后面2个2
     *            也就是如果 c[index] == c[index-1] 就跳过
     *
     * @param candidates
     * @param target
     * @return
     */
    public void combinationSum2BackTrack(int[] candidates, int target, int index, int sum,
                                                        List<Integer> list, List<List<Integer>> result) {
        // 终止条件和之前的组合类似
        if ( sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new ArrayList<>(list));
            return;
        }
        // 递归+回溯
        for (int i = index; i < candidates.length; i++) {
            // 去掉同一层(树的同一层)重复的 如果c[index] == c[index-1] 就跳过
            if (i > index && candidates[i] == candidates[i-1]) {
                continue;
            }
            list.add(candidates[i]);
            sum += candidates[i];
            // 递归
            combinationSum2BackTrack(candidates, target, i + 1, sum, list, result);
            // 回溯
            sum -= list.get(list.size() - 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     *131. 分割回文串
     *
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     *回文串 是正着读和反着读都一样的字符串
     *
     * 输入：s = "aab"
     * 输出：[["a","a","b"],["aa","b"]]
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        partitionBackTrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    public void partitionBackTrack(String s, int startIndex, List<String> list, List<List<String>> result) {
        // 递归终止条件
        if (startIndex >= s.length()) {
            result.add(new ArrayList<>(list));
        }
        // 递归回溯 单层逻辑
        for(int i = startIndex; i < s.length(); i++) {
            if (isBoolean(s,startIndex, i)) {
                list.add(s.substring(startIndex, i + 1));
            } else {
                continue;
            }
            // 递归
            partitionBackTrack(s, i+1 , list, result);
            // 回溯
            list.remove(list.size() - 1);
        }

    }

    //  判断是否回文串
    public boolean isBoolean(String s , int start, int end){
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }


    /**
     * 93. 复原 IP 地址
     *有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     *
     * 输入：s = "25525511135"
     * 输出：["255.255.11.135","255.255.111.35"]
     *
     * 解法：递归+ 回溯 和分割回文串类似
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<List<String>> temp = new ArrayList<>();
        List<String> result = new ArrayList<>();
        restoreIpAddressesBackTrack(s, 0, new ArrayList<>(), temp);
        System.out.println(Arrays.toString(temp.toArray()));
        for (List<String> v : temp) {
            result.add(String.join(".", v));
        }
        return result;
    }

    public void restoreIpAddressesBackTrack(String s, int startIndex, List<String> list, List<List<String>> result) {
        // 已经到字符串最后并且size=4结束就找到里结果
        if (startIndex == s.length() && list.size() == 4) {
            result.add(new ArrayList<>(list));
        }
        for (int i = startIndex; i < s.length(); i++) {
            // 判断是否合法，如果不合法根据合法性判断后面的肯定也不合法就直接返回
            if (isValiedIP(s, startIndex, i)) {
                list.add(s.substring(startIndex, i+1));
                restoreIpAddressesBackTrack(s, i + 1, list, result);
                list.remove(list.size() - 1);
            } else {
                return;
            }
        }
    }

    public boolean isValiedIP(String s, int start , int end){
        if (start == end) {
            return true;
        }
        if (s.charAt(start) != '0' && Integer.parseInt(s.substring(start, end+1)) <= 255) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 78. 子集
     *
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     *
     * 解法：和组合类似，区别在于组合在叶子节点收获结果(达到某一条件如sum size层数之类的)，子集需要在每个节点都收获结果
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // 空集是任何集合的子集
        subsetsBackTrack(nums, 0 , new ArrayList<>(), result);
        return result;
    }

    public void subsetsBackTrack(int[] nums, int startIndex, List<Integer> list, List<List<Integer>>  result) {
        // 子集需要每个全部遍历不需要强制的递归终止条件
        // 递归+回溯
        for(int i = startIndex; i < nums.length; i++) {
            // 收集结果
            list.add(nums[i]);
            result.add(new ArrayList<>(list));
            // 递归
            subsetsBackTrack(nums, i + 1, list, result);
            // 回溯
            list.remove(list.size() - 1);
        }
    }

    /**
     * 90. 子集 II
     *给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     *输入：nums = [1,2,2]
     * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     *
     * 解法: 先排序，遇到重复的跳过(nums[i] == nums[i-1])，后面和求无重复子集一样
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // 空集是任何集合的子集
        Arrays.sort(nums);
        subsetsWithDupBackTrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    public void subsetsWithDupBackTrack(int[] nums,int startIndex, List<Integer> list, List<List<Integer>>  result) {
        // 每个树节点都要收获结果，不需要强制的递归终止条件
        // 单层逻辑
        for (int i = startIndex; i < nums.length; i++) {
            // 去重
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            result.add(new ArrayList<>(list));
            // 递归
            subsetsWithDupBackTrack(nums, i + 1, list, result);
            // 回溯
            list.remove(list.size() - 1);
        }
    }

    /**
     *
     * 46. 全排列
     *
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     *
     * 解法：
     * 排列概念给定{123}
     * { 1 2 3}  {1 3 2}
     * 1.需要全部选取并在后面一次选取之前没有选取过的如从2开始选 后面可以选13，区别于组合只能往后一位选取
     * 可以推出递归中止条件要到达最后一层叶子节点也就是 index = nums.length
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        permuteBackTrack(nums, used, new ArrayList<>(), result);
        return result;
    }

    public void permuteBackTrack(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> result) {
        // 递归中止条件 list里有所有的集合
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        // 单层逻辑 每次从0开始然后选取上次没有选过的元素
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            list.add(nums[i]);
            // 递归
            permuteBackTrack(nums, used,list, result);
            // 回溯
            used[i] = false;
            list.remove(list.size() - 1);

        }
    }

    /**
     * 47. 全排列 II
     *给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     * 
     * notice:去重
     * 
     * 解法：先排序有重复的就跳过这次组合
     * 
     * @param nums
     * @return
     */

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] used = new boolean[nums.length];
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        permuteUniqueBackTrack(nums, used, new ArrayList<>(), result);

        return result;
    }

    public void permuteUniqueBackTrack(int[] nums,boolean[] used, List<Integer> list, List<List<Integer>> result) {
        // 递归中止条件 到叶子节点收获数据
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            System.out.println(Arrays.toString(list.toArray()));
            return;
        }
        // 单层逻辑
        for (int i = 0; i < nums.length; i++) {
            // 树层中遇到相同的并且没有使用过就跳过
            if (i > 0 && nums[i] == nums[i-1] && !used[i - 1] || used[i]) {
                continue;
            }
            list.add(nums[i]);
            used[i] = true;
            // 递归
            permuteUniqueBackTrack(nums, used, list, result);
            // 回溯
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

    /**
     *
     * 51. N 皇后
     *
     * 解法：递归+ 回溯
     * list{4,2,3,}  表示第i+1行 list[i] +1 列, 如1表示第一行第五列
     *
     * notice : 判断斜对角原来的行需要+1
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<Integer>> result = new ArrayList<>();
        int count = 0;
        solveNQueensBackTrack(n, new ArrayList<>(), result);
        // 转化成题目结果
        List<List<String>> result1 = new ArrayList<>();
        for (List<Integer> v : result) {
            List<String> list = new ArrayList<>();
            for(Integer v1: v) {
                // 第几行结果
                String[] s = new String[n];
                Arrays.fill(s, ".");
                s[v1] = "Q";
                // 放入结果集
                list.add(String.join("", s));
            }
            result1.add(list);
            System.out.println(Arrays.toString(list.toArray()));
        }
        System.out.println("一共有解法: " + result.size());
        return result1;
    }

    public void solveNQueensBackTrack(int n, List<Integer> list, List<List<Integer>>result) {
        // 递归中止条件放完n个棋子(也就是只n行)收集结果
        if (list.size() == n) {
            result.add(new ArrayList<>(list));
        }
        // 单层逻辑 从第1列开始放到第n列看是否有符合条件的
        for(int i= 0; i < n; i++) {
            // 如果可以继续放下一行，不可以继续放下一列（也就是继续循环可以省略)
            if (isOk(list, i)) {  // 判断和前面行列是否冲突
                list.add(i);
                solveNQueensBackTrack(n, list, result);  // 递归
                list.remove(list.size() - 1);   // 回溯
            }
        }
    }

    /**
     *
     *
     * @param list
     * @param i  第list.size + 1 行 第i列
     * @return
     */
    public boolean isOk(List<Integer> list, int i) {
        int row = list.size() + 1; // 当前行
        // 这是不同行不需要判断是否相同行，只需要判断是否同一列，同一个斜线
        for (int j = 0; j < list.size(); j++) {
            // 是否同一列
            if (list.get(j) == i) {
                return false;
            }
            // 是否同一斜线 对角线是正方形行差和列差绝对值相同
            // row 从第一行开始，j从第0行开始所以要+1 也可以row-j
            if(Math.abs(row - (j+1)) == Math.abs(list.get(j) - i)) {
                return false;
            }
        }
        return true;
    }

}
