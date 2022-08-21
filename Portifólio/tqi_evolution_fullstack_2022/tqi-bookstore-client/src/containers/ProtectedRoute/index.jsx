import { useNavigate } from "react-router";
import { useAuth } from "../../contexts/UserContext";

const ProtectedRoute = ({ children }) => {
  const navigate = useNavigate();

  const { loggedUser } = useAuth();

  if (!loggedUser) navigate("/");

  return <>{children}</>;
};

export default ProtectedRoute;
