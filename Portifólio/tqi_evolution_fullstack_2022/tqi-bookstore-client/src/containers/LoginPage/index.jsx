import React from "react";
import { useNavigate } from "react-router";
import Button from "../../components/Button";
import Card from "../../components/Card";
import { useAuth } from "../../contexts/UserContext";

const LoginPage = () => {
  const { loggedUser, setLoggedUser } = useAuth();
  const navigate = useNavigate();

  const handleLogin = () => {
    setLoggedUser({ name: "John Doe" });
    navigate("/store");
  };
  const handleLogout = () => setLoggedUser(null);

  const isLoggedIn = loggedUser !== null;

  return (
    <Card>
      <Button onClick={isLoggedIn ? handleLogout : handleLogin} text="Login" />
    </Card>
  );
};

export default LoginPage;
