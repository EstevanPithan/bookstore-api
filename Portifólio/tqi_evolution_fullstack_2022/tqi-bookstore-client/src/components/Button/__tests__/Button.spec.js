import { render, screen } from "@testing-library/react";
import Button from "..";

test("renders learn react link", () => {
  render(<Button text="Teste" />);
  const linkElement = screen.getByText(/Teste/i);
  expect(linkElement).toBeInTheDocument();
});
