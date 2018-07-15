export default class UrlBuilder {
  paths = []
  constructor () {
    this.AppendVersion()
    return this
  }
  AppendVersion () {
    this.paths.push('api/v1')
    return this
  }
  AppendUrl (url) {
    this.paths.push(url)
    return this
  }
  AppendUser (user) {
    this.paths.push(`users/${user.id}`)
    return this
  }
  static Create () {
    return new UrlBuilder()
  }
  ToString () {
    return this.paths.join('/')
  }
  AppendQuery (queryKey, params) {
    let url = params[queryKey] + '?'
    for (let key of Object.keys(params)) {
      if (key === queryKey) {
        continue
      }
      url += key + '=' + params[key] + '&'
    }
    url = url.substring(0, url.length - 1)
    this.paths.push(url)
    return this
  }
}
