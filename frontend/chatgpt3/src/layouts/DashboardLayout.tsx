import { Outlet } from 'react-router-dom'
import Navbar from '@components/Navbar'
import Sidebar from '@components/Sidebar'

export default function DashboardLayout() {
  return (
    <div className="min-h-screen flex flex-col bg-gray-50 text-gray-900 dark:bg-gray-900 dark:text-gray-100 transition-colors duration-300">
      <Navbar />
      <div className="flex flex-1">
        {/* Sidebar */}
        <aside className="w-64 bg-white border-r border-gray-200 dark:bg-gray-800 dark:border-gray-700">
          <Sidebar />
        </aside>

        {/* Main content */}
        <main className="flex-1 bg-gray-50 dark:bg-gray-900 p-6 overflow-y-auto">
          <Outlet />
        </main>
      </div>

      {/* Footer */}
      <footer className="bg-gray-100 dark:bg-gray-900 text-gray-700 dark:text-gray-200 border-t border-gray-200 dark:border-gray-700 text-center py-4">
        © 2025 E-Learn — All rights reserved
      </footer>
    </div>
  )
}

