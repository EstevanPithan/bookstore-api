import axios from 'axios'

export const api = axios.create({
  baseURL: 'http://localhost:8080/',
  headers: { authorization: 'Basic dXNlcjpwYXNzd29yZA==' }
})

// api.interceptors.request.use(async config => {
//   // Declaramos um token manualmente para teste.
//   const token = 'dXNlcjpwYXNzd29yZA=='

//   console.log(token)

//   if (token) {
//     api.defaults.headers.authorization = `Basic ${token}`
//   }

//   return config
// })

// const username = 'user'
// const password = 'password'

// const token = Buffer.from(`${username}:${password}`, 'utf8').toString('base64')

// const url = 'https://...'
// const data = {
//   ...
// }

// axios.post(url, data, {
//   headers: {
//     'Authorization': `Basic ${token}`
//   },
// })
