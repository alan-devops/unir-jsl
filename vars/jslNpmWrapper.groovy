// Expected .npmrc format
// email=user@example.com
// always-auth=true
// registry=http://localhost:8081/repository/registry-name
// _auth=${AUTH_TOKEN}

// nexus-npm-auth-token is a secret text credentials built as
// echo -n user:password | openssl base64

def call(def args) {
  withNPM(npmrcConfig: 'nexus-npmrc') {
    withCredentials([string(credentialsId: 'nexus-npm-auth-token', variable: 'NEXUS_NPM_AUTH_TOKEN')]) {
      def output = sh script: """
          export AUTH_TOKEN=${NEXUS_NPM_AUTH_TOKEN}
          npm ${args}
      """, label: "npm ${args}", returnStdout: true
      return output
    }
  }
}
