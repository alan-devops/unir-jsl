def commitMessage() {
  sh(script: "git log --format=%B -n 1 HEAD | head -n 1", returnStdout: true).trim()
}

def commitAuthor() {
  sh(script: "git log --format=\'%an\' -n 1 HEAD", returnStdout: true).trim()
}

def getRemoteRepoName(){
  def remoteUrl = sh(returnStdout: true, script: 'git config --get remote.origin.url').trim()
  if (remoteUrl.startsWith("git@")) {
      // git@github.com:<repoOwner>/<repoName>.git
      return remoteUrl.split(':')[1].split('/')[1].replaceFirst(/\.git$/, "")
  } else if (remoteUrl.startsWith("http")) {
      // https://github.com/<repoOwner>/<repoName>.git
      return remoteUrl.split('/')[4].replaceFirst(/\.git$/, "")
  } else {
      throw new Exception("Unexpected Git remote URL ${remoteUrl} when parsing repo name")
  }
}

def getRemoteRepoOwner(){
  def remoteUrl = sh(returnStdout: true, script: 'git config --get remote.origin.url').trim()
  if (remoteUrl.startsWith("git@")) {
      // git@github.com:<repoOwner>/<repoName>.git
      return remoteUrl.split(':')[1].split('/')[0]
  } else if (remoteUrl.startsWith("http")) {
      // https://github.com/<repoOwner>/<repoName>.git
      return remoteUrl.split('/')[3]
  } else {
      throw new Exception("Unexpected Git remote URL ${remoteUrl} when parsing repo owner")
  }
}
