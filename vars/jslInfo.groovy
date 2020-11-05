def call () {
    echo "This is an informative step"
    echo """
        Commit details:
        - Author: ${jslGit.commitAuthor()}
        - Message: ${jslGit.commitMessage()}
    """

}
