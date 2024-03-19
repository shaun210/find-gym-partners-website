import React, { useEffect, useState, useRef } from "react";
import { Container, Row, Col, Button, Image } from 'react-bootstrap';
import './ChooseMuscleGroupPage.css';
import bicepLonghead from './Pictures/biceps/longhead.png';
import bicepShorthead from './Pictures/biceps/shorthead.png';
import bicepBrachialis from './Pictures/biceps/brachialis.png';
import tricepLateralhead from './Pictures/triceps/tricepLateralhead.png';
import tricepLonghead from './Pictures/triceps/tricepLonghead.png';
import tricepMedialhead from './Pictures/triceps/tricepMedialhead.png';

const ChooseMuscleGroupPage = () => {
    const [muscleGroup, setMuscleGroup] = useState('');
    const [hoveredBicepMuscle, setHoveredBicepMuscle] = useState('Bicep Longhead');
    const [hoveredTricepMuscle, setHoveredTricepMuscle] = useState('Tricep Longhead');
    const bicepsRef = useRef(null);
    const tricepsRef = useRef(null);
    const chestRef = useRef(null);
    const backRef = useRef(null);
    const shouldersRef = useRef(null);
    const legsRef = useRef(null);
    const absRef = useRef(null);


    const scrollToRef = (ref) => {
        if (ref.current) {
            window.scrollTo({
                top: ref.current.offsetTop,
                behavior: "smooth"
            });
        }
    };

    useEffect(() => {
        console.log(`Selected muscle group: ${muscleGroup}`);
    }, [muscleGroup]);

    return (
        <Container className="choose-muscle-group-container px-0" fluid style={{ width:'80%' }} >
            <Row className="choose-muscle-group-header" style={{marginBottom:'10rem'}}>
                <h1>We did the heavy lifting for you </h1>
            </Row>

            <Row className="muscle-group-introduction-box px-0">
                <Col xs={12} sm={12} md={12} xl ={5} className="muscle-group-introduction-box-left">
                    <div>
                        <h2>Click on the muscle that you want to train</h2>
                        <p>Then embarking on a fitness journey, it's crucial to grasp the complexity of our muscles.</p>
                        
                    </div>
                    <div>
                        <h2>It's crucial to grasp the complexity of our muscles</h2>
                        <p>Contrary to common belief, muscles aren't monolithic structures but intricate compositions of fibers and tissues.</p>
                    </div>
                </Col>
                <Col xs={12} sm={12} md={12} xl={5} className="muscle-group-introduction-box-right">
                    <Button onClick={() => scrollToRef(bicepsRef)}>Biceps</Button>
                    <Button onClick={() => scrollToRef(tricepsRef)}>Triceps</Button>
                    <Button onClick={() => scrollToRef(chestRef)}>Chest</Button>
                    <Button onClick={() => scrollToRef(backRef)}>Back</Button>
                    <Button onClick={() => scrollToRef(shouldersRef)}>Shoulders</Button>
                    <Button onClick={() => scrollToRef(legsRef)}>Legs</Button>
                    <Button onClick={() => scrollToRef(absRef)}>Abs</Button>
                </Col>
            </Row>

            <hr className="bg-primary"></hr>

            <Row ref={bicepsRef} className="main-muscle-box">
                <Row className="main-muscle-box-title mx-0 px-0">
                    <h2>Biceps</h2>
                </Row>
                <Row className="main-muscle-box-content mx-0">
                    <Col xs={12} sm={12} md={6}>
                        <Button className='muscleGroupButton' variant="primary" onMouseEnter={() => setHoveredBicepMuscle('Bicep Longhead')}>Bicep Longhead</Button>
                        <Button className='muscleGroupButton' variant="primary" onMouseEnter={() => setHoveredBicepMuscle('Bicep Shorthead')}>Bicep Shorthead</Button>
                        <Button className='muscleGroupButton' variant="primary" onMouseEnter={() => setHoveredBicepMuscle('Bicep Brachialis')}>Bicep Brachialis</Button>
                    </Col>
    
                    <Col xs={12} sm={12}md={6} className = "main-muscle-box-content-right">
                        {hoveredBicepMuscle === 'Bicep Longhead' && <Image src={bicepLonghead} className="main-muscle-box-content-image"/>}
                        {hoveredBicepMuscle === 'Bicep Shorthead' && <Image src={bicepShorthead} className="main-muscle-box-content-image"/>}
                        {hoveredBicepMuscle === 'Bicep Brachialis' && <Image src={bicepBrachialis} className="main-muscle-box-content-image"/>}
                    </Col>
                </Row>
            </Row>

            <hr className="bg-primary"></hr>

            <Row ref={tricepsRef} className="main-muscle-box">
                <Row className="main-muscle-box-title mx-0 px-0">
                    <h2>Triceps</h2>
                </Row>
                <Row className="main-muscle-box-content mx-0">
                    <Col xs={12} sm={12} md={6}>
                        <Button className='muscleGroupButton' variant="primary" onMouseEnter={() => setHoveredTricepMuscle('Tricep Longhead')}>Tricep Longhead</Button>
                        <Button className='muscleGroupButton' variant="primary" onMouseEnter={() => setHoveredTricepMuscle('Tricep Lateralhead')}>Tricep Lateralhead</Button>
                        <Button className='muscleGroupButton' variant="primary" onMouseEnter={() => setHoveredTricepMuscle('Tricep Medialhead')}>Tricep Medialhead</Button>
                    </Col>
    
                    <Col xs={12} sm={12}md={6}>
                        {hoveredTricepMuscle === 'Tricep Longhead' && <Image src={tricepLonghead} className="main-muscle-box-content-image"/>}
                        {hoveredTricepMuscle === 'Tricep Lateralhead' && <Image src={tricepLateralhead} className="main-muscle-box-content-image"/>}
                        {hoveredTricepMuscle === 'Tricep Medialhead' && <Image src={tricepMedialhead} className="main-muscle-box-content-image"/>}
                    </Col>
                </Row>
            </Row>

            <hr className="bg-primary"></hr>

            <Row ref={chestRef} className="main-muscle-box">
                <Row className="main-muscle-box-title mx-0 px-0">
                    <h2>Chest Exercise</h2>
                </Row>
                <Row className="main-muscle-box-content mx-0">
                    <Col xs={12} sm={12} md={6}>
                        <Button className='muscleGroupButton' variant="primary">Chest</Button>
                    </Col>
    
                    <Col xs={12} sm={12}md={6}>
                        <Image src={bicepLonghead} className="main-muscle-box-content-image"/>
                    </Col>
                </Row>
            </Row>

            <hr className="bg-primary"></hr>

            <Row ref={backRef} className="main-muscle-box">
                <Row className="main-muscle-box-title mx-0 px-0">
                    <h2>Back Exercise</h2>
                </Row>
                <Row className="main-muscle-box-content mx-0">
                    <Col xs={12} sm={12} md={6}>
                        <Button className='muscleGroupButton' variant="primary">Back</Button>
                    </Col>
    
                    <Col xs={12} sm={12}md={6}>
                        <Image src={bicepLonghead} className="main-muscle-box-content-image"/>
                    </Col>
                </Row>
            </Row>

            <hr className="bg-primary"></hr>

            <Row ref={shouldersRef} className="main-muscle-box">
                <Row className="main-muscle-box-title mx-0 px-0">
                    <h2>Shoulders Exercise</h2>
                </Row>
                <Row className="main-muscle-box-content mx-0">
                    <Col xs={12} sm={12} md={6}>
                        <Button className='muscleGroupButton' variant="primary">Shoulders</Button>
                    </Col>
    
                    <Col xs={12} sm={12}md={6}>
                        <Image src={bicepLonghead} className="main-muscle-box-content-image"/>
                    </Col>
                </Row>
            </Row>

            <hr className="bg-primary"></hr>

            <Row ref={legsRef} className="main-muscle-box">
                <Row className="main-muscle-box-title mx-0 px-0">
                    <h2>Legs Exercise</h2>
                </Row>
                <Row className="main-muscle-box-content mx-0">
                    <Col xs={12} sm={12} md={6}>
                        <Button className='muscleGroupButton' variant="primary">Legs</Button>
                    </Col>
    
                    <Col xs={12} sm={12}md={6}>
                        <Image src={bicepLonghead} className="main-muscle-box-content-image"/>
                    </Col>
                </Row>
            </Row>

            <hr className="bg-primary"></hr>

            <Row ref={absRef} className="main-muscle-box">
                <Row className="main-muscle-box-title mx-0 px-0">
                    <h2>Abs Exercise</h2>
                </Row>
                <Row className="main-muscle-box-content mx-0">
                    <Col xs={12} sm={12} md={6}>
                        <Button className='muscleGroupButton' variant="primary">Abs</Button>
                    </Col>
    
                    <Col xs={12} sm={12}md={6}>
                        <Image src={bicepLonghead} className="main-muscle-box-content-image"/>
                    </Col>
                </Row>
            </Row>
            
        </Container>
    );
    
}

export default ChooseMuscleGroupPage;