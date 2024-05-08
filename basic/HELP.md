
## Git Basic 

* Clone source code from git repository
    ````
    git clone <git_repo> 
* Add git repository to source code existed
    ````
    git init 
    git remote add origin <git_repo>
    git push -f origin <branch_name>
* Update source code to repository git
    ````
    git pull
* View file change or new file
    ````
    git status
* Add file to git
    ````
    git add .
    git add <file_name_1> <file_name_2>
* Add message commit
    ````
    git commit -m "message"
* Change message commit
    ````
    git commit --amend -m "new message"
* Add file use message commit old
    ````
    git commit --amend --no-edit
* Push file add to git
    ````
    git push -u origin <branch_name>
* View all branch in local
    ````
    git branch
* Change name current branch
    ````
    git branch -M new_branch_name
    
    git push origin new_branch_name
    
    # Delete old branch
    git push origin --delete old_branch_name
    ````
* View all branch (local and git repository)
    ````
    git branch -a
* Create branch in local from current branch
    > git branch <branch_name>
* Switch to branch (existed branch)
    > git checkout <branch_name>

    > git switch <branch_name>
* Create new branch and switch to new branch
    > git checkout -b <branch_name>

    > git switch -c <branch_name>
* Change file to original state
  ````
    git checkout -- .
    git checkout -- <file1> <file2> <folder>
  ````
* Show log history commit
    ````
    git log
    
    git log --oneline

    git log --graph

    git reflog
    ````
* Delete branch from local
    ````
    git branch -d <branh_name>
* Delete branch from git repository
    ````
    git push origin --delete remote-branch-name

## Git Advance
* Merge source code from branch A to main
  ````
    git checkout main
    git pull 
    git merge A
    <fix conflict code>
    git add .
    git commit -m "fix: conflict merge code A"
    git push
  ````
* Rebase source code from branch A to main (main is base branch)
  ````
    git checkout A
    git pull
    git rebase main
    <fix conflict>
    git add .
    git rebase --continue
    git push -f
    git checkout main
    git merge --no-ff A
  ````
* Save change or new file to cache
    ````
    git stash save 'name_stash'
    
    git stash list

    git stash apply <stash_id>
    
    git stash pop

    git stash drop <stash_id>

    git stash clear
    ````
*  Reset code to commit in history (khi chưa có ai pull về)
  ````
    git reset --soft <commit_id>

    git reset <commit_id>

    git reset --hard <commit_id>
  ````
* Reset code to commit in history (người khác đã pull về -> tao 1 commit moi)
  ````
    git revert <commit_hash>
  ````
* Apply change from one to many commit from branch A to main branch 
    ````
    git checkout main
  
    # Lấy commit cuối cùng ở branch A và merge vào branch main
    git cherry-pick A
  
    # Nếu muốn thêm 1 vài commit, không liên tục
    git cherry-pick <A_commit_id_1> <A_commit_id_2>
  
    # Nếu muốn thêm 1 loạt commit lần lượt cạnh nhau
    git cherry-pick commit_id1...commit_id5
  
* git worktree with multiple branch
   ````
   # view all worktree added
   git worktree list
   
   # add branch to worktree (-f: force if exists in worktree)
   git worktree add [-f] [-b <new-branch>] <path> [<branch>]
  
   # remove worktree
   git worktree remove <name-worktree>
   ````
## Tình huống trong quá trình sử dụng git quản lý version source code
1. Khi đang phát triển chức năng trên nhánh feature và checkout sang nhánh khác để hotfix mà không muốn commit code hiện tại
   * Lưu file source code thay đổi: 
    ````
    git stash save "description stash"
    ````
   * Danh sách stash đã lưu: 
    ````
    git stash list
    ````
   * Áp dụng lại các thay đổi stash: 
    ````
    git stash apply <stash_id>
    git stash pop 
    ````
   * Xóa stash theo id: 
    ````
    git stash drop <stash_id>
    ````
   * Xóa toàn bộ stash:
    ````
    git stash clear
    ````
2. Phát triển nhiều nhánh cùng lúc
    ````
    # Lấy cấu trúc của thư mục .git (ko chứa source phát triển)
    git clone --bare <git-repository> <folder-name>
   
    #
    git worktree list
   
    # Thêm branch A vào worktree
    git worktree add [<branchA>]
   
    # Thêm branch B vào worktree
    git worktree add [<branchB>]
    ````
3. Viết lại lịch sử commit trên branch trước khi pull request
   * squash commit
    ````
    git log --oneline
    
    # Gop N commit dau tien can gop
    git rebase -i HEAD~N
    
    # Giu 1 pick dau tien va thay doi nhưng pick con lai thanh s va luu lai
   
    # Xoa cac mo ta cu va thay doi bang mo ta moi (them # o truoc hoac xoa dong mo ta)
   
    # kiem tra lai log history
    git log --oneline
   
    # push thay doi len git repository
    git push -f 
    ````
4. Sử dụng git rebase, git merge trong quá trình phát triển nhóm để được linear history
   * Document: [link](https://www.bitsnbites.eu/a-tidy-linear-git-history/)
5. git flow và commit chuẩn trong quá trình phát triển chức năng
   * Document git follow: [link](https://anonystick.com/blog-developer/cac-phuong-phap-hay-nhat-cho-git-trong-nhom-cach-su-dung-git-flow-dung-cach-2022071961655104)
     * Branch main: Dành cho production, được hợp nhất từ các nhánh khác, không nên sửa đổi trực tiếp
     * Branch develop: Nhánh phát triển chính của team trong công ty và chứa tất cả code sẽ được phát hành cho phiên bản tiếp theo. Nhánh này hợp nhất với các nhánh khác ở dạng phát triển thêm tính năng feature
     * Branch feature: Nhánh này chủ yếu được sử dụng để phát triển một chức năng mới. Sau khi quá trình phát triển hoàn tất, hợp nhất trở lại nhánh develop và tham gia các tính năng tiếp theo
     * Branch release: Khi cần phát hành bản phát hành mới, tạo một nhánh bản release dựa trên nhánh develop. Sau khi branch release hoàn tất, sẽ merge nó vào nhánh main và develop.
     * Branch hotfix: Khi tìm thấy một lỗi mới trong dự án (trong môi trường production) thì cần tạo một nhánh hotfix. Sau khi hotfix hoàn tất, hợp nhất trở lại các nhánh main và develop, vì vậy những thay đổi trong Hotfix sẽ vào bản phát hành tiếp theo.
   * Document commit convention: [link](https://www.conventionalcommits.org/en/v1.0.0/)
   ````
    <type>[optional scope]: <description>

    [optional body]

    [optional footer(s)]
   ````
   1. type: fix:, feat:, build:, chore:, ci:, docs:, style:, refactor:, perf:, test:  
6. Phát triển nhầm nhánh và revert lại source code
   * git reset (3 trường hợp: soft, hard , default)
   * git cherry-pick