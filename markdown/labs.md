# Labs

- [Week 1](labs/week1.md) (6/20 - 6/24)
- [Week 2](labs/week2.md) (6/27 - 7/1)
- [Week 3](labs/week3.md) (7/5 - 7/8)
- [Week 4](labs/week4.md) (7/11 - 7/15)
- [Week 5](labs/week5.md) (7/18 - 7/22)
- [Week 6](labs/week6.md) (7/25 - 7/29)
- [Week 7](labs/week7.md) (8/1 - 8/5)


---

#### How to download soution code posted after you made your fork:

1. Navigate to your local repo from the command line
1. Run `git remote -v` and note there is only one remote listed, your fork with the name `origin`
1. Run `git remote add upstream <url of original repo, not your fork>` to add an additional remote named `upstream` which points to the original repo
1. Run `git remote -v` again to confirm that you now have two remotes, with `upstream` linked to the original repo
1. Run `git pull upstream master` to pull in changes from the `upstream` remote's `master` branch
1. You shouldn't encounter merge conflicts, but if you do, [resolve them](https://help.github.com/articles/resolving-a-merge-conflict-from-the-command-line/) and do a commit after the resolution
1. Run `git push origin master` to push the changes you got from	`upstream` out to your fork on Github
