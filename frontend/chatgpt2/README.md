
# E-Learn React Front-End v2 (College-style, React-only)

This is a **React-only** (Vite) front-end implementing a college-style LMS UI: assigned courses (others locked), quizzes, assignments, and progress tracking. It connects to your backend via REST — **no backend is included**.

## Run
```bash
npm install
cp .env.example .env   # set VITE_API_BASE_URL (no trailing slash)
npm run dev
```

## Endpoints expected (adjust `src/services/api.js`)
- `POST /api/auth/login` → `{ accessToken, roles, profile }`
- `POST /api/auth/register`
- `GET  /api/courses?q=&page=&size=`
- `GET  /api/courses/assigned`
- `GET  /api/courses/{id}`
- `GET  /api/progress/{courseId}`
- `GET  /api/me/enrollments`
- `GET  /api/courses/{id}/quizzes`
- `POST /api/quizzes/{quizId}/submit`
- `GET  /api/courses/{id}/assignments`
- `POST /api/assignments/{id}/submit`
- `GET  /api/admin/users`
- `GET  /api/admin/categories`
- `POST /api/admin/assignCourse`

## Notes
- Tokens: reads `accessToken` from localStorage and sends `Authorization: Bearer <token>`.
- Assignment submission uses JSON payload for simplicity — replace with file upload when your backend is ready.
- Styling is custom CSS; integrate Tailwind/MUI if you prefer.
