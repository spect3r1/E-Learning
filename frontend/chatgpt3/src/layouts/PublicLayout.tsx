import { Outlet } from 'react-router-dom'
import Navbar from '@components/Navbar'

export default function PublicLayout() {
  return (
    <div className="min-h-screen flex flex-col">
      <Navbar />
      <main className="container-app py-6 flex-1">
        <Outlet />
      </main>
      <footer className="border-t border-gray-100 text-center py-6 text-sm text-gray-500 bg-white">
        © 2025 E‑Learn. All rights reserved.
      </footer>
    </div>
  )
}