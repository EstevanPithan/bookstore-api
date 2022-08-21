import ButtonStyles from './styled.jsx'


const Button = ({text, onClick}) => {
  return  <ButtonStyles onClick={onClick}>
        {text}
    </ButtonStyles>
}

export default Button

