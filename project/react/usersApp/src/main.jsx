import React from 'react'
import ReactDOM from 'react-dom/client'
import './style.css'
import { UserApps } from './UserApps'
import { BrowserRouter } from 'react-router-dom'
import { AuthProvider } from './auth/context/AuthProvider'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <BrowserRouter>
    <AuthProvider>
      <UserApps />
    </AuthProvider>
    </BrowserRouter>
  </React.StrictMode>,
)
