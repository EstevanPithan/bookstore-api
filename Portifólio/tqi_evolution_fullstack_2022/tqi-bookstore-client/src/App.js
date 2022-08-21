import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import LoginPage from './containers/LoginPage'
import Homepage from './containers/Homepage'
import BooksRegister from './containers/BooksRegister'
import ClientsRegister from './containers/ClientsRegister'
import NewSellOrder from './containers/NewSellOrder'
import NewBuyOrder from './containers/NewBuyOrder'
import { UserProvider } from './contexts/UserContext'

function App() {
  return (
    <UserProvider>
      <Router>
        <Routes>
          <Route path="/" element={<Homepage />} />
          <Route path="/store" element={<Homepage />} />
          <Route path="/newBook" element={<BooksRegister />} />
          <Route path="/newClient" element={<ClientsRegister />} />
          <Route path="/newSellOrder" element={<NewSellOrder />} />
          <Route path="/newBuyOrder" element={<NewBuyOrder />} />
        </Routes>
      </Router>
    </UserProvider>
  )
}

export default App
