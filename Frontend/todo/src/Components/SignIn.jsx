import {
    Flex,
    Box,
    FormControl,
    FormLabel,
    Input,
    Checkbox,
    Stack,
    Button,
    Heading,
    Text,
    useColorModeValue,
  } from '@chakra-ui/react';
import { async } from 'q';
import { Link } from 'react-router-dom';


 const signIn = async () =>{

  const email = document.querySelector(".email").value;
  const password = document.querySelector(".password").value;

  const authToken = window.btoa(`${email}:${password}`);

  fetch("http://localhost:8888/user/signIn", {
    headers: {
      Authorization: `Basic ${authToken}`,
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      
      const token = response.headers.get("Authorization");


      localStorage.setItem("token", token);
    })
    .catch((error) => {
      alert(error.message);
    });
 }
  
  export default function SignIn() {
    return (
      <Flex
        minH={'100vh'}
        align={'start'}
        justify={'center'}
        bg={useColorModeValue('gray.50', 'gray.800')}>
        <Stack spacing={8} mx={'auto'} maxW={'lg'} py={12} px={6}>
          <Stack align={'center'}>
            <Heading textAlign={"center"} fontSize={'4xl'}>Sign in to your account</Heading>
            <Text fontSize={'lg'} color={'gray.600'}>
              Don't have Account?  <Link to={"/SignUp"} style={{color:"blue"}}>SignUp</Link>
            </Text>
          </Stack>
          <Box
            rounded={'lg'}
            bg={useColorModeValue('white', 'gray.700')}
            boxShadow={'lg'}
            p={8}>
            <Stack spacing={4}>
              <FormControl id="email">
                <FormLabel>Email address</FormLabel>
                <Input className='email' type="email" />
              </FormControl>
              <FormControl id="password">
                <FormLabel>Password</FormLabel>
                <Input className='password' type="password" />
              </FormControl>
              <Stack spacing={10}>
                <Stack
                  direction={{ base: 'column', sm: 'row' }}
                  align={'start'}
                  justify={'space-between'}>
                  <Checkbox>Remember me</Checkbox>
                  <Link color={'blue.400'}>Forgot password?</Link>
                </Stack>
                <Button
                 onClick={signIn}
                  bg={'blue.400'}
                  color={'white'}
                  _hover={{
                    bg: 'blue.500',
                  }}>
                  Sign in
                </Button>
              </Stack>
            </Stack>
          </Box>
        </Stack>
      </Flex>
    );
  }