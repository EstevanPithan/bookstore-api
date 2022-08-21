import styled from  "styled-components"

const ButtonStyles = styled.button`
    border: 1px solid #000000;
    border-radius: 5px;
    color: #fff;
    background-color: #28a812;
    transition: all 0.2s ease-in-out ;
    font-size: 1rem;
    height: 2rem;
    width: 10ch;

    &:hover{
        filter: (brightness(0.8));
        transform: scale(1.1);
    }
`

export default ButtonStyles