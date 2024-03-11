import { ButtonProps } from "../propsTypes/ButtonProps";

const Button = ({
  border = "border-transparent",
  bgColor = "bg-blue-500",
  color = "text-white",
  content,
  height = "h-auto",
  onClick,
  radius = "rounded-md",
  width = "w-auto",
}: ButtonProps) => {
  return (
    <button
      onClick={onClick}
      className={`px-4 py-2 ${border} ${bgColor} ${color} ${radius} ${width} ${height}`}
    >
      {content}
    </button>
  );
};

export default Button;
