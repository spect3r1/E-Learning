import useAuthStore from '@stores/auth'

export default function Profile() {
  const { user } = useAuthStore()
  if (!user) return <p>Not logged in</p>
  return (
    <div className="card">
      <h2 className="text-xl font-semibold mb-4">Profile</h2>
      <div className="flex items-center gap-4">
        <img src={user.avatarUrl || 'https://via.placeholder.com/80'} width="80" height="80" className="rounded-full" alt="avatar" />
        <div className="space-y-1">
          <p><strong>Name:</strong> {user.name}</p>
          <p><strong>Email:</strong> {user.email}</p>
          <p><strong>Role:</strong> {user.role}</p>
        </div>
      </div>
    </div>
  )
}