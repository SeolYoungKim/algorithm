package graph;

/**
 * <a href="https://leetcode.com/problems/number-of-islands/">numberOfIslands</a>
 */
public class NumberOfIslandsTest {
    /**
     * 동서남북이 연결된 그래프라고 봐도 됨
     * - 가장먼저 '1'을 찾은 뒤 동서남북을 모두 탐색하면서 재귀 호출하는 구조
     * - 동서남북을 모두 탐색해 '0'을 만나면 재귀를 빠져나온다 (백트래킹)
     * - 이미 방문했던 곳은 '1'이 아닌 값으로 마킹한다 (방문한 곳은 육지('1')가 아닌 것으로 만들기만 하면 된다)
     * - dfs()를 빠져나오면 해당 위치에서 탐색 가능한 모든 육지를 탐색한 것이므로 섬의 카운트를 1 증가시킨다
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        // 행렬 전부 탐색
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // 만약 육지(1)인 경우 dfs 시작
                if (grid[i][j] == '1') {
                    dfs(i, j, grid);
                    // 재귀 dfs가 모두 끝나면 섬 하나 완성
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(int i, int j, char[][] grid) {
        // 현재 위치가 그리드 밖이거나, 물(0)인 경우 종료
        if (i < 0 || i >= grid.length || j < 0 || j >= grid.length || grid[i][j] == '0') {
            return;
        }

        // 한번만 탐색하기 위해 이미 탐색한 지점은 물(0)로 변경
        grid[i][j] = '0';

        // 동서남북 모두 재귀로 탐색
        dfs(i, j + 1, grid);  // 동
        dfs(i, j - 1, grid);  // 서
        dfs(i + 1, j, grid);  // 남
        dfs(i - 1, j, grid);  // 북
    }
}
