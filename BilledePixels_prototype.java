/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semester_projekt;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import javax.imageio.ImageIO;

/**
 *
 * @author euc
 */
public class BilledePixels_prototype {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedImage binary = null; //her bliver billedet indlæst
        try {
            BufferedImage img = ImageIO.read(new File("C:\\Users\\frede\\Videos\\SDU Robot Diplom\\Semester projekt 1\\billeder\\Rainbow.png"));
            binary = new BufferedImage(img.getWidth(), img.getHeight(),
                    BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D g = binary.createGraphics();
            g.drawImage(img, 0, 0, null);
            HashSet<Integer> colors = new HashSet<>();
            int color;
            for (int y = 0; y < binary.getHeight(); y++) {
                for (int x = 0; x < binary.getWidth(); x++) {
                    color = binary.getRGB(x, y);

     /*              if (color == -1) {
                        binary.setRGB(x, y, -1);
                    } else {
                        binary.setRGB(x, y, -16777216);
                    }*/
                    //System.out.println(color); slået fra fordi ellers tager det for lang tid at køre programmet
                    colors.add(color);
                }
            }
            System.out.println(colors.size());
        } catch (IOException e) {
        }
        try {//her oprettes der et nyt billede som er behandlet i sort og hvidt
            File s = new File("C:\\Users\\frede\\Videos\\SDU Robot Diplom\\Semester projekt 1\\billeder\\new picture.jpg");
            ImageIO.write(binary, "jpg", s);
            System.out.println("File writing completed ");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        File input = new File("C:\\Users\\frede\\Videos\\SDU Robot Diplom\\Semester projekt 1\\billeder\\new picture.jpg");
        BufferedImage image = ImageIO.read(input);
        BufferedImage resized = resize(image, 50, 100);//ændre størrelsen på billedet
        File output = new File("C:\\Users\\frede\\Videos\\SDU Robot Diplom\\Semester projekt 1\\billeder\\scale picture.jpg");
        ImageIO.write(resized, "jpg", output);
        int[][] compute = compute(output);//her bliver pixelværdierne for billedet skrevet ind til en tekstfil
        /*try {
            System.out.println("connection test");
            RobotClient R1 = new RobotClient("192.161.1.15", 11159);
            R1.connect();
            if (R1.isConnected()) {
                R1.write("x41y02x42y02x43y02x44y02x45y02x46y02x47y02x48y02x49y02x10y03x11y03x12y03x13y03x14y03x15y03x16y03x17y03x18y03x42y03x43y03x44y03x45y03x46y03x47y03x48y03x06y04x07y04x08y04x09y04x10y04x11y04x12y04x13y04x14y04x15y04x16y04x17y04x18y04x43y04x44y04x45y04x46y04x47y04x48y04x93y04x07y05x08y05x09y05x10y05x11y05x12y05x13y05x14y05x15y05x16y05x17y05x18y05x43y05x44y05x45y05x46y05x47y05x92y05x93y05x94y05x08y06x09y06x10y06x11y06x12y06x13y06x14y06x15y06x16y06x17y06x18y06x44y06x45y06x46y06x50y06x51y06x91y06x92y06x93y06x94y06x09y07x10y07x11y07x12y07x13y07x14y07x15y07x16y07x17y07x18y07x29y07x45y07x46y07x49y07x50y07x51y07x52y07x90y07x91y07x92y07x93y07x94y07x95y07x09y08x10y08x11y08x12y08x13y08x14y08x15y08x16y08x17y08x18y08x28y08x29y08x30y08x48y08x49y08x50y08x51y08x52y08x72y08x73y08x74y08x75y08x76y08x90y08x91y08x92y08x93y08x94y08x95y08x10y09x11y09x12y09x13y09x14y09x15y09x16y09x17y09x28y09x29y09x30y09x48y09x49y09x50y09x51y09x52y09x53y09x66y09x67y09x68y09x69y09x70y09x71y09x72y09x73y09x74y09x75y09x76y09x77y09x89y09x90y09x91y09x92y09x93y09x94y09x95y09x11y10x12y10x13y10x14y10x15y10x16y10x17y10x27y10x28y10x29y10x30y10x31y10x47y10x48y10x49y10x50y10x51y10x52y10x53y10x54y10x59y10x60y10x61y10x62y10x63y10x64y10x65y10x66y10x67y10x68y10x69y10x70y10x71y10x72y10x73y10x74y10x75y10x76y10x77y10x88y10x89y10x90y10x91y10x92y10x93y10x94y10x95y10x96y10x12y11x13y11x14y11x15y11x16y11x17y11x26y11x27y11x28y11x29y11x30y11x31y11x32y11x46y11x47y11x48y11x49y11x50y11x51y11x52y11x53y11x54y11x55y11x59y11x60y11x61y11x62y11x63y11x64y11x65y11x66y11x67y11x68y11x69y11x70y11x71y11x72y11x73y11x74y11x75y11x76y11x77y11x78y11x87y11x88y11x89y11x90y11x91y11x92y11x93y11x94y11x95y11x96y11x12y12x13y12x14y12x15y12x16y12x17y12x26y12x27y12x28y12x29y12x30y12x31y12x32y12x45y12x46y12x47y12x48y12x49y12x50y12x51y12x52y12x53y12x54y12x55y12x60y12x61y12x62y12x63y12x64y12x65y12x66y12x67y12x68y12x69y12x70y12x71y12x72y12x73y12x74y12x75y12x76y12x77y12x78y12x86y12x87y12x88y12x89y12x90y12x91y12x92y12x93y12x94y12x95y12x96y12x97y12x13y13x14y13x15y13x16y13x17y13x25y13x26y13x27y13x28y13x29y13x30y13x31y13x32y13x33y13x45y13x46y13x47y13x48y13x49y13x50y13x51y13x52y13x53y13x54y13x55y13x56y13x62y13x63y13x64y13x65y13x66y13x67y13x68y13x69y13x70y13x71y13x72y13x73y13x74y13x75y13x76y13x77y13x78y13x85y13x86y13x87y13x88y13x89y13x90y13x91y13x92y13x93y13x94y13x95y13x96y13x97y13x14y14x15y14x16y14x17y14x25y14x26y14x27y14x28y14x29y14x30y14x31y14x32y14x33y14x34y14x44y14x45y14x46y14x47y14x48y14x49y14x50y14x51y14x52y14x53y14x54y14x55y14x56y14x57y14x64y14x65y14x66y14x67y14x68y14x69y14x70y14x71y14x72y14x73y14x74y14x75y14x76y14x77y14x78y14x84y14x85y14x86y14x87y14x88y14x89y14x90y14x91y14x92y14x93y14x94y14x95y14x96y14x97y14x15y15x16y15x24y15x25y15x26y15x27y15x28y15x29y15x30y15x31y15x32y15x33y15x34y15x43y15x44y15x45y15x46y15x47y15x48y15x49y15x50y15x51y15x52y15x53y15x54y15x55y15x56y15x57y15x65y15x66y15x67y15x68y15x69y15x70y15x71y15x72y15x73y15x74y15x75y15x76y15x77y15x78y15x15y16x16y16x23y16x24y16x25y16x26y16x27y16x28y16x29y16x30y16x31y16x32y16x33y16x34y16x35y16x42y16x43y16x44y16x45y16x46y16x47y16x48y16x49y16x50y16x51y16x52y16x53y16x54y16x55y16x56y16x57y16x58y16x66y16x67y16x68y16x69y16x70y16x71y16x72y16x73y16x74y16x75y16x76y16x77y16x78y16x16y17x23y17x24y17x25y17x26y17x27y17x28y17x29y17x30y17x31y17x32y17x33y17x34y17x35y17x36y17x41y17x42y17x43y17x44y17x45y17x46y17x47y17x48y17x49y17x50y17x51y17x52y17x53y17x54y17x55y17x64y17x65y17x66y17x67y17x68y17x69y17x70y17x71y17x72y17x73y17x74y17x75y17x76y17x77y17x78y17x22y18x23y18x24y18x25y18x26y18x27y18x28y18x29y18x30y18x31y18x32y18x33y18x34y18x35y18x36y18x37y18x41y18x42y18x43y18x44y18x45y18x46y18x47y18x48y18x49y18x50y18x51y18x61y18x62y18x63y18x64y18x65y18x66y18x67y18x68y18x69y18x70y18x71y18x72y18x73y18x74y18x75y18x76y18x77y18x78y18x05y19x06y19x21y19x22y19x23y19x24y19x25y19x26y19x27y19x28y19x29y19x30y19x31y19x32y19x33y19x34y19x35y19x36y19x37y19x40y19x41y19x42y19x43y19x44y19x45y19x46y19x47y19x58y19x59y19x60y19x61y19x62y19x63y19x64y19x65y19x66y19x67y19x68y19x69y19x72y19x73y19x74y19x75y19x76y19x77y19x78y19x05y20x06y20x07y20x08y20x09y20x10y20x21y20x22y20x23y20x24y20x25y20x26y20x27y20x28y20x29y20x30y20x31y20x32y20x33y20x34y20x35y20x36y20x37y20x38y20x39y20x40y20x41y20x42y20x43y20x56y20x57y20x58y20x59y20x60y20x61y20x62y20x63y20x64y20x65y20x66y20x67y20x68y20x69y20x74y20x75y20x76y20x77y20x78y20x05y21x06y21x07y21x08y21x09y21x10y21x11y21x12y21x13y21x14y21x20y21x21y21x22y21x23y21x24y21x25y21x26y21x27y21x28y21x29y21x30y21x31y21x32y21x33y21x34y21x35y21x36y21x37y21x38y21x39y21x53y21x54y21x55y21x56y21x57y21x58y21x59y21x60y21x61y21x62y21x63y21x64y21x65y21x66y21x67y21x68y21x75y21x76y21x77y21x78y21x05y22x06y22x07y22x08y22x09y22x10y22x11y22x12y22x13y22x14y22x15y22x16y22x17y22x18y22x19y22x20y22x21y22x22y22x23y22x24y22x25y22x26y22x27y22x28y22x29y22x30y22x31y22x32y22x33y22x34y22x35y22x36y22x37y22x38y22x39y22x54y22x55y22x56y22x57y22x58y22x59y22x60y22x61y22x62y22x63y22x64y22x65y22x66y22x67y22x68y22x77y22x78y22x94y22x95y22x05y23x06y23x07y23x08y23x09y23x10y23x11y23x12y23x13y23x14y23x15y23x16y23x17y23x18y23x19y23x20y23x21y23x22y23x23y23x24y23x25y23x26y23x27y23x28y23x29y23x30y23x31y23x32y23x33y23x34y23x35y23x36y23x37y23x38y23x39y23x40y23x55y23x56y23x57y23x58y23x59y23x60y23x61y23x62y23x63y23x64y23x65y23x66y23x67y23x68y23x93y23x94y23x95y23x05y24x06y24x07y24x08y24x09y24x10y24x11y24x12y24x13y24x14y24x15y24x16y24x17y24x18y24x19y24x20y24x21y24x22y24x23y24x24y24x25y24x26y24x27y24x28y24x29y24x30y24x31y24x32y24x33y24x34y24x35y24x36y24x37y24x38y24x39y24x40y24x41y24x56y24x57y24x58y24x59y24x60y24x61y24x62y24x63y24x64y24x65y24x66y24x67y24x92y24x93y24x94y24x95y24x05y25x06y25x07y25x08y25x09y25x10y25x11y25x12y25x13y25x14y25x15y25x16y25x17y25x18y25x19y25x20y25x21y25x22y25x23y25x24y25x25y25x26y25x27y25x28y25x29y25x30y25x31y25x32y25x33y25x34y25x35y25x36y25x37y25x38y25x39y25x40y25x41y25x57y25x58y25x59y25x60y25x61y25x62y25x63y25x64y25x65y25x66y25x67y25x90y25x91y25x92y25x93y25x94y25x95y25x05y26x06y26x07y26x08y26x09y26x10y26x11y26x12y26x13y26x14y26x15y26x16y26x17y26x18y26x19y26x20y26x21y26x22y26x23y26x24y26x25y26x26y26x27y26x28y26x29y26x30y26x31y26x32y26x33y26x34y26x35y26x36y26x37y26x38y26x39y26x40y26x41y26x42y26x58y26x59y26x60y26x61y26x62y26x63y26x64y26x65y26x66y26x67y26x89y26x90y26x91y26x92y26x93y26x94y26x95y26x05y27x06y27x07y27x08y27x09y27x10y27x11y27x12y27x13y27x14y27x15y27x16y27x17y27x18y27x19y27x20y27x21y27x22y27x23y27x24y27x25y27x26y27x27y27x28y27x29y27x30y27x31y27x32y27x33y27x34y27x35y27x36y27x37y27x38y27x39y27x40y27x41y27x42y27x43y27x60y27x61y27x62y27x63y27x64y27x65y27x66y27x87y27x88y27x89y27x90y27x91y27x92y27x93y27x94y27x95y27x04y28x05y28x06y28x07y28x08y28x09y28x10y28x11y28x12y28x13y28x14y28x15y28x16y28x17y28x18y28x19y28x20y28x21y28x22y28x23y28x24y28x25y28x26y28x27y28x28y28x29y28x30y28x31y28x32y28x33y28x34y28x35y28x36y28x37y28x38y28x39y28x40y28x41y28x42y28x43y28x61y28x62y28x63y28x64y28x65y28x66y28x86y28x87y28x88y28x89y28x90y28x91y28x92y28x93y28x94y28x95y28x04y29x05y29x06y29x07y29x08y29x09y29x10y29x11y29x12y29x13y29x21y29x22y29x33y29x34y29x35y29x36y29x37y29x38y29x39y29x40y29x41y29x42y29x43y29x44y29x62y29x63y29x64y29x65y29x66y29x85y29x86y29x87y29x88y29x89y29x90y29x91y29x92y29x93y29x94y29x95y29x04y30x05y30x06y30x07y30x08y30x09y30x10y30x11y30x20y30x21y30x22y30x23y30x63y30x64y30x65y30x83y30x84y30x85y30x86y30x87y30x88y30x89y30x90y30x91y30x92y30x93y30x94y30x95y30x04y31x05y31x06y31x07y31x08y31x09y31x19y31x20y31x21y31x22y31x23y31x64y31x65y31x71y31x83y31x84y31x85y31x86y31x87y31x88y31x89y31x90y31x91y31x92y31x93y31x94y31x95y31x04y32x05y32x06y32x07y32x18y32x19y32x20y32x21y32x22y32x23y32x24y32x70y32x71y32x72y32x86y32x87y32x88y32x89y32x90y32x91y32x92y32x93y32x94y32x95y32x96y32x04y33x05y33x17y33x18y33x19y33x20y33x21y33x22y33x23y33x24y33x48y33x49y33x50y33x51y33x52y33x53y33x54y33x55y33x56y33x70y33x71y33x72y33x89y33x90y33x91y33x92y33x93y33x94y33x95y33x96y33x16y34x17y34x18y34x19y34x20y34x21y34x22y34x23y34x24y34x25y34x35y34x36y34x37y34x38y34x39y34x40y34x41y34x42y34x43y34x44y34x45y34x46y34x47y34x48y34x49y34x50y34x51y34x52y34x53y34x54y34x55y34x70y34x71y34x72y34x73y34x91y34x92y34x93y34x94y34x95y34x96y34x15y35x16y35x17y35x18y35x19y35x20y35x21y35x22y35x23y35x24y35x25y35x33y35x34y35x35y35x36y35x37y35x38y35x39y35x40y35x41y35x42y35x43y35x44y35x45y35x46y35x47y35x48y35x49y35x50y35x51y35x52y35x53y35x54y35x70y35x71y35x72y35x73y35x74y35x94y35x95y35x96y35x14y36x15y36x16y36x17y36x18y36x19y36x20y36x21y36x22y36x23y36x24y36x25y36x26y36x35y36x36y36x37y36x38y36x39y36x40y36x41y36x42y36x43y36x44y36x45y36x46y36x47y36x48y36x49y36x50y36x69y36x70y36x71y36x72y36x73y36x74y36x75y36x14y37x15y37x16y37x17y37x18y37x19y37x20y37x21y37x22y37x23y37x24y37x25y37x26y37x31y37x32y37x33y37x37y37x38y37x39y37x40y37x41y37x42y37x43y37x44y37x45y37x46y37x47y37x48y37x49y37x50y37x51y37x52y37x68y37x69y37x70y37x71y37x72y37x73y37x74y37x75y37x76y37x13y38x14y38x15y38x16y38x17y38x18y38x19y38x20y38x21y38x22y38x23y38x24y38x25y38x26y38x27y38x31y38x32y38x33y38x34y38x39y38x40y38x41y38x42y38x43y38x44y38x45y38x46y38x47y38x48y38x49y38x50y38x51y38x68y38x69y38x70y38x71y38x72y38x73y38x74y38x75y38x76y38x12y39x13y39x14y39x15y39x16y39x17y39x18y39x19y39x20y39x21y39x22y39x23y39x24y39x25y39x26y39x27y39x30y39x31y39x32y39x33y39x34y39x35y39x41y39x42y39x43y39x44y39x45y39x46y39x47y39x48y39x49y39x50y39x67y39x68y39x69y39x70y39x71y39x72y39x73y39x74y39x75y39x76y39x77y39x11y40x12y40x13y40x14y40x15y40x16y40x17y40x18y40x19y40x20y40x21y40x22y40x23y40x24y40x25y40x26y40x27y40x28y40x29y40x30y40x31y40x32y40x33y40x34y40x35y40x36y40x43y40x44y40x45y40x46y40x47y40x48y40x49y40x66y40x67y40x68y40x69y40x70y40x71y40x72y40x73y40x74y40x75y40x76y40x77y40x78y40x79y40x10y41x11y41x12y41x13y41x14y41x15y41x16y41x17y41x18y41x19y41x20y41x21y41x22y41x23y41x24y41x25y41x26y41x27y41x28y41x29y41x30y41x31y41x32y41x33y41x34y41x35y41x36y41x37y41x45y41x46y41x47y41x48y41x66y41x67y41x68y41x69y41x70y41x71y41x72y41x73y41x74y41x75y41x76y41x77y41x78y41x79y41x80y41x11y42x12y42x13y42x14y42x15y42x16y42x17y42x18y42x19y42x20y42x21y42x22y42x23y42x24y42x25y42x26y42x27y42x28y42x29y42x30y42x31y42x32y42x33y42x34y42x35y42x36y42x37y42x38y42x47y42x63y42x64y42x65y42x66y42x67y42x68y42x69y42x70y42x71y42x72y42x73y42x74y42x75y42x76y42x77y42x78y42x79y42x80y42x81y42x17y43x18y43x19y43x20y43x21y43x22y43x23y43x24y43x25y43x26y43x27y43x28y43x29y43x30y43x31y43x32y43x33y43x34y43x35y43x36y43x37y43x38y43x39y43x62y43x63y43x64y43x65y43x66y43x67y43x68y43x69y43x70y43x71y43x72y43x73y43x74y43x75y43x76y43x77y43x78y43x79y43x80y43x81y43x82y43x23y44x24y44x25y44x26y44x27y44x28y44x29y44x30y44x31y44x32y44x33y44x34y44x35y44x36y44x37y44x38y44x39y44x40y44x61y44x62y44x63y44x64y44x65y44x66y44x67y44x68y44x69y44x70y44x71y44x72y44x73y44x74y44x75y44x76y44x77y44x78y44x79y44x80y44x81y44x82y44x83y44x26y45x27y45x28y45x29y45x30y45x31y45x32y45x33y45x34y45x35y45x36y45x37y45x38y45x39y45x40y45x41y45x61y45x62y45x63y45x64y45x65y45x66y45x67y45x68y45x69y45x70y45x71y45x72y45x73y45x74y45x75y45x76y45x77y45x78y45x79y45x80y45x81y45x82y45x83y45x84y45x25y46x26y46x27y46x28y46x29y46x30y46x31y46x32y46x33y46x34y46x35y46x36y46x37y46x38y46x39y46x40y46x41y46x42y46x60y46x61y46x62y46x63y46x64y46x65y46x66y46x67y46x68y46x69y46x70y46x71y46x72y46x73y46x74y46x75y46x76y46x77y46x78y46x79y46x80y46x81y46x82y46x83y46x84y46x85y46x25y47x26y47x27y47x28y47x29y47x30y47x31y47x32y47x33y47x34y47x35y47x36y47x37y47x38y47x39y47x40y47x41y47x42y47x43y47x60y47x61y47x62y47x63y47x64y47x65y47");
//            R1.write("x 41 y 02 x 42 y 02 x 43 y 02 x 44 y 02 x 45 y 02 x 46 y 02 x 47 y 02 x 48 y 02 x 49 y 02 x 10 y 03 x 11 y 03 x 12 y 03 x 13 y 03 x 14 y 03 x 15 y 03 x 16 y 03 x 17 y 03 x 18 y 03 x 42 y 03 x 43 y 03 x 44 y 03 x 45 y 03 x 46 y 03 x 47 y 03 x 48 y 03 x 06 y 04 x 07 y 04 x 08 y 04 x 09 y 04 x 10 y 04 x 11 y 04 x 12 y 04 x 13 y 04 x 14 y 04 x 15 y 04 x 16 y 04 x 17 y 04 x 18 y 04 x 43 y 04 x 44 y 04 x 45 y 04 x 46 y 04 x 47 y 04 x 48 y 04 x 93 y 04 x 07 y 05 x 08 y 05 x 09 y 05 x 10 y 05 x 11 y 05 x 12 y 05 x 13 y 05 x 14 y 05 x 15 y 05 x 16 y 05 x 17 y 05 x 18 y 05 x 43 y 05 x 44 y 05 x 45 y 05 x 46 y 05 x 47 y 05 x 92 y 05 x 93 y 05 x 94 y 05 x 08 y 06 x 09 y 06 x 10 y 06 x 11 y 06 x 12 y 06 x 13 y 06 x 14 y 06 x 15 y 06 x 16 y 06 x 17 y 06 x 18 y 06 x 44 y 06 x 45 y 06 x 46 y 06 x 50 y 06 x 51 y 06 x 91 y 06 x 92 y 06 x 93 y 06 x 94 y 06 x 09 y 07 x 10 y 07 x 11 y 07 x 12 y 07 x 13 y 07 x 14 y 07 x 15 y 07 x 16 y 07 x 17 y 07 x 18 y 07 x 29 y 07 x 45 y 07 x 46 y 07 x 49 y 07 x 50 y 07 x 51 y 07 x 52 y 07 x 90 y 07 x 91 y 07 x 92 y 07 x 93 y 07 x 94 y 07 x 95 y 07 x 09 y 08 x 10 y 08 x 11 y 08 x 12 y 08 x 13 y 08 x 14 y 08 x 15 y 08 x 16 y 08 x 17 y 08 x 18 y 08 x 28 y 08 x 29 y 08 x 30 y 08 x 48 y 08 x 49 y 08 x 50 y 08 x 51 y 08 x 52 y 08 x 72 y 08 x 73 y 08 x 74 y 08 x 75 y 08 x 76 y 08 x 90 y 08 x 91 y 08 x 92 y 08 x 93 y 08 x 94 y 08 x 95 y 08 x 10 y 09 x 11 y 09 x 12 y 09 x 13 y 09 x 14 y 09 x 15 y 09 x 16 y 09 x 17 y 09 x 28 y 09 x 29 y 09 x 30 y 09 x 48 y 09 x 49 y 09 x 50 y 09 x 51 y 09 x 52 y 09 x 53 y 09 x 66 y 09 x 67 y 09 x 68 y 09 x 69 y 09 x 70 y 09 x 71 y 09 x 72 y 09 x 73 y 09 x 74 y 09 x 75 y 09 x 76 y 09 x 77 y 09 x 89 y 09 x 90 y 09 x 91 y 09 x 92 y 09 x 93 y 09 x 94 y 09 x 95 y 09 x 11 y 10 x 12 y 10 x 13 y 10 x 14 y 10 x 15 y 10 x 16 y 10 x 17 y 10 x 27 y 10 x 28 y 10 x 29 y 10 x 30 y 10 x 31 y 10 x 47 y 10 x 48 y 10 x 49 y 10 x 50 y 10 x 51 y 10 x 52 y 10 x 53 y 10 x 54 y 10 x 59 y 10 x 60 y 10 x 61 y 10 x 62 y 10 x 63 y 10 x 64 y 10 x 65 y 10 x 66 y 10 x 67 y 10 x 68 y 10 x 69 y 10 x 70 y 10 x 71 y 10 x 72 y 10 x 73 y 10 x 74 y 10 x 75 y 10 x 76 y 10 x 77 y 10 x 88 y 10 x 89 y 10 x 90 y 10 x 91 y 10 x 92 y 10 x 93 y 10 x 94 y 10 x 95 y 10 x 96 y 10 x 12 y 11 x 13 y 11 x 14 y 11 x 15 y 11 x 16 y 11 x 17 y 11 x 26 y 11 x 27 y 11 x 28 y 11 x 29 y 11 x 30 y 11 x 31 y 11 x 32 y 11 x 46 y 11 x 47 y 11 x 48 y 11 x 49 y 11 x 50 y 11 x 51 y 11 x 52 y 11 x 53 y 11 x 54 y 11 x 55 y 11 x 59 y 11 x 60 y 11 x 61 y 11 x 62 y 11 x 63 y 11 x 64 y 11 x 65 y 11 x 66 y 11 x 67 y 11 x 68 y 11 x 69 y 11 x 70 y 11 x 71 y 11 x 72 y 11 x 73 y 11 x 74 y 11 x 75 y 11 x 76 y 11 x 77 y 11 x 78 y 11 x 87 y 11 x 88 y 11 x 89 y 11 x 90 y 11 x 91 y 11 x 92 y 11 x 93 y 11 x 94 y 11 x 95 y 11 x 96 y 11 x 12 y 12 x 13 y 12 x 14 y 12 x 15 y 12 x 16 y 12 x 17 y 12 x 26 y 12 x 27 y 12 x 28 y 12 x 29 y 12 x 30 y 12 x 31 y 12 x 32 y 12 x 45 y 12 x 46 y 12 x 47 y 12 x 48 y 12 x 49 y 12 x 50 y 12 x 51 y 12 x 52 y 12 x 53 y 12 x 54 y 12 x 55 y 12 x 60 y 12 x 61 y 12 x 62 y 12 x 63 y 12 x 64 y 12 x 65 y 12 x 66 y 12 x 67 y 12 x 68 y 12 x 69 y 12 x 70 y 12 x 71 y 12 x 72 y 12 x 73 y 12 x 74 y 12 x 75 y 12 x 76 y 12 x 77 y 12 x 78 y 12 x 86 y 12 x 87 y 12 x 88 y 12 x 89 y 12 x 90 y 12 x 91 y 12 x 92 y 12 x 93 y 12 x 94 y 12 x 95 y 12 x 96 y 12 x 97 y 12 x 13 y 13 x 14 y 13 x 15 y 13 x 16 y 13 x 17 y 13 x 25 y 13 x 26 y 13 x 27 y 13 x 28 y 13 x 29 y 13 x 30 y 13 x 31 y 13 x 32 y 13 x 33 y 13 x 45 y 13 x 46 y 13 x 47 y 13 x 48 y 13 x 49 y 13 x 50 y 13 x 51 y 13 x 52 y 13 x 53 y 13 x 54 y 13 x 55 y 13 x 56 y 13 x 62 y 13 x 63 y 13 x 64 y 13 x 65 y 13 x 66 y 13 x 67 y 13 x 68 y 13 x 69 y 13 x 70 y 13 x 71 y 13 x 72 y 13 x 73 y 13 x 74 y 13 x 75 y 13 x 76 y 13 x 77 y 13 x 78 y 13 x 85 y 13 x 86 y 13 x 87 y 13 x 88 y 13 x 89 y 13 x 90 y 13 x 91 y 13 x 92 y 13 x 93 y 13 x 94 y 13 x 95 y 13 x 96 y 13 x 97 y 13 x 14 y 14 x 15 y 14 x 16 y 14 x 17 y 14 x 25 y 14 x 26 y 14 x 27 y 14 x 28 y 14 x 29 y 14 x 30 y 14 x 31 y 14 x 32 y 14 x 33 y 14 x 34 y 14 x 44 y 14 x 45 y 14 x 46 y 14 x 47 y 14 x 48 y 14 x 49 y 14 x 50 y 14 x 51 y 14 x 52 y 14 x 53 y 14 x 54 y 14 x 55 y 14 x 56 y 14 x 57 y 14 x 64 y 14 x 65 y 14 x 66 y 14 x 67 y 14 x 68 y 14 x 69 y 14 x 70 y 14 x 71 y 14 x 72 y 14 x 73 y 14 x 74 y 14 x 75 y 14 x 76 y 14 x 77 y 14 x 78 y 14 x 84 y 14 x 85 y 14 x 86 y 14 x 87 y 14 x 88 y 14 x 89 y 14 x 90 y 14 x 91 y 14 x 92 y 14 x 93 y 14 x 94 y 14 x 95 y 14 x 96 y 14 x 97 y 14 x 15 y 15 x 16 y 15 x 24 y 15 x 25 y 15 x 26 y 15 x 27 y 15 x 28 y 15 x 29 y 15 x 30 y 15 x 31 y 15 x 32 y 15 x 33 y 15 x 34 y 15 x 43 y 15 x 44 y 15 x 45 y 15 x 46 y 15 x 47 y 15 x 48 y 15 x 49 y 15 x 50 y 15 x 51 y 15 x 52 y 15 x 53 y 15 x 54 y 15 x 55 y 15 x 56 y 15 x 57 y 15 x 65 y 15 x 66 y 15 x 67 y 15 x 68 y 15 x 69 y 15 x 70 y 15 x 71 y 15 x 72 y 15 x 73 y 15 x 74 y 15 x 75 y 15 x 76 y 15 x 77 y 15 x 78 y 15 x 15 y 16 x 16 y 16 x 23 y 16 x 24 y 16 x 25 y 16 x 26 y 16 x 27 y 16 x 28 y 16 x 29 y 16 x 30 y 16 x 31 y 16 x 32 y 16 x 33 y 16 x 34 y 16 x 35 y 16 x 42 y 16 x 43 y 16 x 44 y 16 x 45 y 16 x 46 y 16 x 47 y 16 x 48 y 16 x 49 y 16 x 50 y 16 x 51 y 16 x 52 y 16 x 53 y 16 x 54 y 16 x 55 y 16 x 56 y 16 x 57 y 16 x 58 y 16 x 66 y 16 x 67 y 16 x 68 y 16 x 69 y 16 x 70 y 16 x 71 y 16 x 72 y 16 x 73 y 16 x 74 y 16 x 75 y 16 x 76 y 16 x 77 y 16 x 78 y 16 x 16 y 17 x 23 y 17 x 24 y 17 x 25 y 17 x 26 y 17 x 27 y 17 x 28 y 17 x 29 y 17 x 30 y 17 x 31 y 17 x 32 y 17 x 33 y 17 x 34 y 17 x 35 y 17 x 36 y 17 x 41 y 17 x 42 y 17 x 43 y 17 x 44 y 17 x 45 y 17 x 46 y 17 x 47 y 17 x 48 y 17 x 49 y 17 x 50 y 17 x 51 y 17 x 52 y 17 x 53 y 17 x 54 y 17 x 55 y 17 x 64 y 17 x 65 y 17 x 66 y 17 x 67 y 17 x 68 y 17 x 69 y 17 x 70 y 17 x 71 y 17 x 72 y 17 x 73 y 17 x 74 y 17 x 75 y 17 x 76 y 17 x 77 y 17 x 78 y 17 x 22 y 18 x 23 y 18 x 24 y 18 x 25 y 18 x 26 y 18 x 27 y 18 x 28 y 18 x 29 y 18 x 30 y 18 x 31 y 18 x 32 y 18 x 33 y 18 x 34 y 18 x 35 y 18 x 36 y 18 x 37 y 18 x 41 y 18 x 42 y 18 x 43 y 18 x 44 y 18 x 45 y 18 x 46 y 18 x 47 y 18 x 48 y 18 x 49 y 18 x 50 y 18 x 51 y 18 x 61 y 18 x 62 y 18 x 63 y 18 x 64 y 18 x 65 y 18 x 66 y 18 x 67 y 18 x 68 y 18 x 69 y 18 x 70 y 18 x 71 y 18 x 72 y 18 x 73 y 18 x 74 y 18 x 75 y 18 x 76 y 18 x 77 y 18 x 78 y 18 x 05 y 19 x 06 y 19 x 21 y 19 x 22 y 19 x 23 y 19 x 24 y 19 x 25 y 19 x 26 y 19 x 27 y 19 x 28 y 19 x 29 y 19 x 30 y 19 x 31 y 19 x 32 y 19 x 33 y 19 x 34 y 19 x 35 y 19 x 36 y 19 x 37 y 19 x 40 y 19 x 41 y 19 x 42 y 19 x 43 y 19 x 44 y 19 x 45 y 19 x 46 y 19 x 47 y 19 x 58 y 19 x 59 y 19 x 60 y 19 x 61 y 19 x 62 y 19 x 63 y 19 x 64 y 19 x 65 y 19 x 66 y 19 x 67 y 19 x 68 y 19 x 69 y 19 x 72 y 19 x 73 y 19 x 74 y 19 x 75 y 19 x 76 y 19 x 77 y 19 x 78 y 19 x 05 y 20 x 06 y 20 x 07 y 20 x 08 y 20 x 09 y 20 x 10 y 20 x 21 y 20 x 22 y 20 x 23 y 20 x 24 y 20 x 25 y 20 x 26 y 20 x 27 y 20 x 28 y 20 x 29 y 20 x 30 y 20 x 31 y 20 x 32 y 20 x 33 y 20 x 34 y 20 x 35 y 20 x 36 y 20 x 37 y 20 x 38 y 20 x 39 y 20 x 40 y 20 x 41 y 20 x 42 y 20 x 43 y 20 x 56 y 20 x 57 y 20 x 58 y 20 x 59 y 20 x 60 y 20 x 61 y 20 x 62 y 20 x 63 y 20 x 64 y 20 x 65 y 20 x 66 y 20 x 67 y 20 x 68 y 20 x 69 y 20 x 74 y 20 x 75 y 20 x 76 y 20 x 77 y 20 x 78 y 20 x 05 y 21 x 06 y 21 x 07 y 21 x 08 y 21 x 09 y 21 x 10 y 21 x 11 y 21 x 12 y 21 x 13 y 21 x 14 y 21 x 20 y 21 x 21 y 21 x 22 y 21 x 23 y 21 x 24 y 21 x 25 y 21 x 26 y 21 x 27 y 21 x 28 y 21 x 29 y 21 x 30 y 21 x 31 y 21 x 32 y 21 x 33 y 21 x 34 y 21 x 35 y 21 x 36 y 21 x 37 y 21 x 38 y 21 x 39 y 21 x 53 y 21 x 54 y 21 x 55 y 21 x 56 y 21 x 57 y 21 x 58 y 21 x 59 y 21 x 60 y 21 x 61 y 21 x 62 y 21 x 63 y 21 x 64 y 21 x 65 y 21 x 66 y 21 x 67 y 21 x 68 y 21 x 75 y 21 x 76 y 21 x 77 y 21 x 78 y 21 x 05 y 22 x 06 y 22 x 07 y 22 x 08 y 22 x 09 y 22 x 10 y 22 x 11 y 22 x 12 y 22 x 13 y 22 x 14 y 22 x 15 y 22 x 16 y 22 x 17 y 22 x 18 y 22 x 19 y 22 x 20 y 22 x 21 y 22 x 22 y 22 x 23 y 22 x 24 y 22 x 25 y 22 x 26 y 22 x 27 y 22 x 28 y 22 x 29 y 22 x 30 y 22 x 31 y 22 x 32 y 22 x 33 y 22 x 34 y 22 x 35 y 22 x 36 y 22 x 37 y 22 x 38 y 22 x 39 y 22 x 54 y 22 x 55 y 22 x 56 y 22 x 57 y 22 x 58 y 22 x 59 y 22 x 60 y 22 x 61 y 22 x 62 y 22 x 63 y 22 x 64 y 22 x 65 y 22 x 66 y 22 x 67 y 22 x 68 y 22 x 77 y 22 x 78 y 22 x 94 y 22 x 95 y 22 x 05 y 23 x 06 y 23 x 07 y 23 x 08 y 23 x 09 y 23 x 10 y 23 x 11 y 23 x 12 y 23 x 13 y 23 x 14 y 23 x 15 y 23 x 16 y 23 x 17 y 23 x 18 y 23 x 19 y 23 x 20 y 23 x 21 y 23 x 22 y 23 x 23 y 23 x 24 y 23 x 25 y 23 x 26 y 23 x 27 y 23 x 28 y 23 x 29 y 23 x 30 y 23 x 31 y 23 x 32 y 23 x 33 y 23 x 34 y 23 x 35 y 23 x 36 y 23 x 37 y 23 x 38 y 23 x 39 y 23 x 40 y 23 x 55 y 23 x 56 y 23 x 57 y 23 x 58 y 23 x 59 y 23 x 60 y 23 x 61 y 23 x 62 y 23 x 63 y 23 x 64 y 23 x 65 y 23 x 66 y 23 x 67 y 23 x 68 y 23 x 93 y 23 x 94 y 23 x 95 y 23 x 05 y 24 x 06 y 24 x 07 y 24 x 08 y 24 x 09 y 24 x 10 y 24 x 11 y 24 x 12 y 24 x 13 y 24 x 14 y 24 x 15 y 24 x 16 y 24 x 17 y 24 x 18 y 24 x 19 y 24 x 20 y 24 x 21 y 24 x 22 y 24 x 23 y 24 x 24 y 24 x 25 y 24 x 26 y 24 x 27 y 24 x 28 y 24 x 29 y 24 x 30 y 24 x 31 y 24 x 32 y 24 x 33 y 24 x 34 y 24 x 35 y 24 x 36 y 24 x 37 y 24 x 38 y 24 x 39 y 24 x 40 y 24 x 41 y 24 x 56 y 24 x 57 y 24 x 58 y 24 x 59 y 24 x 60 y 24 x 61 y 24 x 62 y 24 x 63 y 24 x 64 y 24 x 65 y 24 x 66 y 24 x 67 y 24 x 92 y 24 x 93 y 24 x 94 y 24 x 95 y 24 x 05 y 25 x 06 y 25 x 07 y 25 x 08 y 25 x 09 y 25 x 10 y 25 x 11 y 25 x 12 y 25 x 13 y 25 x 14 y 25 x 15 y 25 x 16 y 25 x 17 y 25 x 18 y 25 x 19 y 25 x 20 y 25 x 21 y 25 x 22 y 25 x 23 y 25 x 24 y 25 x 25 y 25 x 26 y 25 x 27 y 25 x 28 y 25 x 29 y 25 x 30 y 25 x 31 y 25 x 32 y 25 x 33 y 25 x 34 y 25 x 35 y 25 x 36 y 25 x 37 y 25 x 38 y 25 x 39 y 25 x 40 y 25 x 41 y 25 x 57 y 25 x 58 y 25 x 59 y 25 x 60 y 25 x 61 y 25 x 62 y 25 x 63 y 25 x 64 y 25 x 65 y 25 x 66 y 25 x 67 y 25 x 90 y 25 x 91 y 25 x 92 y 25 x 93 y 25 x 94 y 25 x 95 y 25 x 05 y 26 x 06 y 26 x 07 y 26 x 08 y 26 x 09 y 26 x 10 y 26 x 11 y 26 x 12 y 26 x 13 y 26 x 14 y 26 x 15 y 26 x 16 y 26 x 17 y 26 x 18 y 26 x 19 y 26 x 20 y 26 x 21 y 26 x 22 y 26 x 23 y 26 x 24 y 26 x 25 y 26 x 26 y 26 x 27 y 26 x 28 y 26 x 29 y 26 x 30 y 26 x 31 y 26 x 32 y 26 x 33 y 26 x 34 y 26 x 35 y 26 x 36 y 26 x 37 y 26 x 38 y 26 x 39 y 26 x 40 y 26 x 41 y 26 x 42 y 26 x 58 y 26 x 59 y 26 x 60 y 26 x 61 y 26 x 62 y 26 x 63 y 26 x 64 y 26 x 65 y 26 x 66 y 26 x 67 y 26 x 89 y 26 x 90 y 26 x 91 y 26 x 92 y 26 x 93 y 26 x 94 y 26 x 95 y 26 x 05 y 27 x 06 y 27 x 07 y 27 x 08 y 27 x 09 y 27 x 10 y 27 x 11 y 27 x 12 y 27 x 13 y 27 x 14 y 27 x 15 y 27 x 16 y 27 x 17 y 27 x 18 y 27 x 19 y 27 x 20 y 27 x 21 y 27 x 22 y 27 x 23 y 27 x 24 y 27 x 25 y 27 x 26 y 27 x 27 y 27 x 28 y 27 x 29 y 27 x 30 y 27 x 31 y 27 x 32 y 27 x 33 y 27 x 34 y 27 x 35 y 27 x 36 y 27 x 37 y 27 x 38 y 27 x 39 y 27 x 40 y 27 x 41 y 27 x 42 y 27 x 43 y 27 x 60 y 27 x 61 y 27 x 62 y 27 x 63 y 27 x 64 y 27 x 65 y 27 x 66 y 27 x 87 y 27 x 88 y 27 x 89 y 27 x 90 y 27 x 91 y 27 x 92 y 27 x 93 y 27 x 94 y 27 x 95 y 27 x 04 y 28 x 05 y 28 x 06 y 28 x 07 y 28 x 08 y 28 x 09 y 28 x 10 y 28 x 11 y 28 x 12 y 28 x 13 y 28 x 14 y 28 x 15 y 28 x 16 y 28 x 17 y 28 x 18 y 28 x 19 y 28 x 20 y 28 x 21 y 28 x 22 y 28 x 23 y 28 x 24 y 28 x 25 y 28 x 26 y 28 x 27 y 28 x 28 y 28 x 29 y 28 x 30 y 28 x 31 y 28 x 32 y 28 x 33 y 28 x 34 y 28 x 35 y 28 x 36 y 28 x 37 y 28 x 38 y 28 x 39 y 28 x 40 y 28 x 41 y 28 x 42 y 28 x 43 y 28 x 61 y 28 x 62 y 28 x 63 y 28 x 64 y 28 x 65 y 28 x 66 y 28 x 86 y 28 x 87 y 28 x 88 y 28 x 89 y 28 x 90 y 28 x 91 y 28 x 92 y 28 x 93 y 28 x 94 y 28 x 95 y 28 x 04 y 29 x 05 y 29 x 06 y 29 x 07 y 29 x 08 y 29 x 09 y 29 x 10 y 29 x 11 y 29 x 12 y 29 x 13 y 29 x 21 y 29 x 22 y 29 x 33 y 29 x 34 y 29 x 35 y 29 x 36 y 29 x 37 y 29 x 38 y 29 x 39 y 29 x 40 y 29 x 41 y 29 x 42 y 29 x 43 y 29 x 44 y 29 x 62 y 29 x 63 y 29 x 64 y 29 x 65 y 29 x 66 y 29 x 85 y 29 x 86 y 29 x 87 y 29 x 88 y 29 x 89 y 29 x 90 y 29 x 91 y 29 x 92 y 29 x 93 y 29 x 94 y 29 x 95 y 29 x 04 y 30 x 05 y 30 x 06 y 30 x 07 y 30 x 08 y 30 x 09 y 30 x 10 y 30 x 11 y 30 x 20 y 30 x 21 y 30 x 22 y 30 x 23 y 30 x 63 y 30 x 64 y 30 x 65 y 30 x 83 y 30 x 84 y 30 x 85 y 30 x 86 y 30 x 87 y 30 x 88 y 30 x 89 y 30 x 90 y 30 x 91 y 30 x 92 y 30 x 93 y 30 x 94 y 30 x 95 y 30 x 04 y 31 x 05 y 31 x 06 y 31 x 07 y 31 x 08 y 31 x 09 y 31 x 19 y 31 x 20 y 31 x 21 y 31 x 22 y 31 x 23 y 31 x 64 y 31 x 65 y 31 x 71 y 31 x 83 y 31 x 84 y 31 x 85 y 31 x 86 y 31 x 87 y 31 x 88 y 31 x 89 y 31 x 90 y 31 x 91 y 31 x 92 y 31 x 93 y 31 x 94 y 31 x 95 y 31 x 04 y 32 x 05 y 32 x 06 y 32 x 07 y 32 x 18 y 32 x 19 y 32 x 20 y 32 x 21 y 32 x 22 y 32 x 23 y 32 x 24 y 32 x 70 y 32 x 71 y 32 x 72 y 32 x 86 y 32 x 87 y 32 x 88 y 32 x 89 y 32 x 90 y 32 x 91 y 32 x 92 y 32 x 93 y 32 x 94 y 32 x 95 y 32 x 96 y 32 x 04 y 33 x 05 y 33 x 17 y 33 x 18 y 33 x 19 y 33 x 20 y 33 x 21 y 33 x 22 y 33 x 23 y 33 x 24 y 33 x 48 y 33 x 49 y 33 x 50 y 33 x 51 y 33 x 52 y 33 x 53 y 33 x 54 y 33 x 55 y 33 x 56 y 33 x 70 y 33 x 71 y 33 x 72 y 33 x 89 y 33 x 90 y 33 x 91 y 33 x 92 y 33 x 93 y 33 x 94 y 33 x 95 y 33 x 96 y 33 x 16 y 34 x 17 y 34 x 18 y 34 x 19 y 34 x 20 y 34 x 21 y 34 x 22 y 34 x 23 y 34 x 24 y 34 x 25 y 34 x 35 y 34 x 36 y 34 x 37 y 34 x 38 y 34 x 39 y 34 x 40 y 34 x 41 y 34 x 42 y 34 x 43 y 34 x 44 y 34 x 45 y 34 x 46 y 34 x 47 y 34 x 48 y 34 x 49 y 34 x 50 y 34 x 51 y 34 x 52 y 34 x 53 y 34 x 54 y 34 x 55 y 34 x 70 y 34 x 71 y 34 x 72 y 34 x 73 y 34 x 91 y 34 x 92 y 34 x 93 y 34 x 94 y 34 x 95 y 34 x 96 y 34 x 15 y 35 x 16 y 35 x 17 y 35 x 18 y 35 x 19 y 35 x 20 y 35 x 21 y 35 x 22 y 35 x 23 y 35 x 24 y 35 x 25 y 35 x 33 y 35 x 34 y 35 x 35 y 35 x 36 y 35 x 37 y 35 x 38 y 35 x 39 y 35 x 40 y 35 x 41 y 35 x 42 y 35 x 43 y 35 x 44 y 35 x 45 y 35 x 46 y 35 x 47 y 35 x 48 y 35 x 49 y 35 x 50 y 35 x 51 y 35 x 52 y 35 x 53 y 35 x 54 y 35 x 70 y 35 x 71 y 35 x 72 y 35 x 73 y 35 x 74 y 35 x 94 y 35 x 95 y 35 x 96 y 35 x 14 y 36 x 15 y 36 x 16 y 36 x 17 y 36 x 18 y 36 x 19 y 36 x 20 y 36 x 21 y 36 x 22 y 36 x 23 y 36 x 24 y 36 x 25 y 36 x 26 y 36 x 35 y 36 x 36 y 36 x 37 y 36 x 38 y 36 x 39 y 36 x 40 y 36 x 41 y 36 x 42 y 36 x 43 y 36 x 44 y 36 x 45 y 36 x 46 y 36 x 47 y 36 x 48 y 36 x 49 y 36 x 50 y 36 x 69 y 36 x 70 y 36 x 71 y 36 x 72 y 36 x 73 y 36 x 74 y 36 x 75 y 36 x 14 y 37 x 15 y 37 x 16 y 37 x 17 y 37 x 18 y 37 x 19 y 37 x 20 y 37 x 21 y 37 x 22 y 37 x 23 y 37 x 24 y 37 x 25 y 37 x 26 y 37 x 31 y 37 x 32 y 37 x 33 y 37 x 37 y 37 x 38 y 37 x 39 y 37 x 40 y 37 x 41 y 37 x 42 y 37 x 43 y 37 x 44 y 37 x 45 y 37 x 46 y 37 x 47 y 37 x 48 y 37 x 49 y 37 x 50 y 37 x 51 y 37 x 52 y 37 x 68 y 37 x 69 y 37 x 70 y 37 x 71 y 37 x 72 y 37 x 73 y 37 x 74 y 37 x 75 y 37 x 76 y 37 x 13 y 38 x 14 y 38 x 15 y 38 x 16 y 38 x 17 y 38 x 18 y 38 x 19 y 38 x 20 y 38 x 21 y 38 x 22 y 38 x 23 y 38 x 24 y 38 x 25 y 38 x 26 y 38 x 27 y 38 x 31 y 38 x 32 y 38 x 33 y 38 x 34 y 38 x 39 y 38 x 40 y 38 x 41 y 38 x 42 y 38 x 43 y 38 x 44 y 38 x 45 y 38 x 46 y 38 x 47 y 38 x 48 y 38 x 49 y 38 x 50 y 38 x 51 y 38 x 68 y 38 x 69 y 38 x 70 y 38 x 71 y 38 x 72 y 38 x 73 y 38 x 74 y 38 x 75 y 38 x 76 y 38 x 12 y 39 x 13 y 39 x 14 y 39 x 15 y 39 x 16 y 39 x 17 y 39 x 18 y 39 x 19 y 39 x 20 y 39 x 21 y 39 x 22 y 39 x 23 y 39 x 24 y 39 x 25 y 39 x 26 y 39 x 27 y 39 x 30 y 39 x 31 y 39 x 32 y 39 x 33 y 39 x 34 y 39 x 35 y 39 x 41 y 39 x 42 y 39 x 43 y 39 x 44 y 39 x 45 y 39 x 46 y 39 x 47 y 39 x 48 y 39 x 49 y 39 x 50 y 39 x 67 y 39 x 68 y 39 x 69 y 39 x 70 y 39 x 71 y 39 x 72 y 39 x 73 y 39 x 74 y 39 x 75 y 39 x 76 y 39 x 77 y 39 x 11 y 40 x 12 y 40 x 13 y 40 x 14 y 40 x 15 y 40 x 16 y 40 x 17 y 40 x 18 y 40 x 19 y 40 x 20 y 40 x 21 y 40 x 22 y 40 x 23 y 40 x 24 y 40 x 25 y 40 x 26 y 40 x 27 y 40 x 28 y 40 x 29 y 40 x 30 y 40 x 31 y 40 x 32 y 40 x 33 y 40 x 34 y 40 x 35 y 40 x 36 y 40 x 43 y 40 x 44 y 40 x 45 y 40 x 46 y 40 x 47 y 40 x 48 y 40 x 49 y 40 x 66 y 40 x 67 y 40 x 68 y 40 x 69 y 40 x 70 y 40 x 71 y 40 x 72 y 40 x 73 y 40 x 74 y 40 x 75 y 40 x 76 y 40 x 77 y 40 x 78 y 40 x 79 y 40 x 10 y 41 x 11 y 41 x 12 y 41 x 13 y 41 x 14 y 41 x 15 y 41 x 16 y 41 x 17 y 41 x 18 y 41 x 19 y 41 x 20 y 41 x 21 y 41 x 22 y 41 x 23 y 41 x 24 y 41 x 25 y 41 x 26 y 41 x 27 y 41 x 28 y 41 x 29 y 41 x 30 y 41 x 31 y 41 x 32 y 41 x 33 y 41 x 34 y 41 x 35 y 41 x 36 y 41 x 37 y 41 x 45 y 41 x 46 y 41 x 47 y 41 x 48 y 41 x 66 y 41 x 67 y 41 x 68 y 41 x 69 y 41 x 70 y 41 x 71 y 41 x 72 y 41 x 73 y 41 x 74 y 41 x 75 y 41 x 76 y 41 x 77 y 41 x 78 y 41 x 79 y 41 x 80 y 41 x 11 y 42 x 12 y 42 x 13 y 42 x 14 y 42 x 15 y 42 x 16 y 42 x 17 y 42 x 18 y 42 x 19 y 42 x 20 y 42 x 21 y 42 x 22 y 42 x 23 y 42 x 24 y 42 x 25 y 42 x 26 y 42 x 27 y 42 x 28 y 42 x 29 y 42 x 30 y 42 x 31 y 42 x 32 y 42 x 33 y 42 x 34 y 42 x 35 y 42 x 36 y 42 x 37 y 42 x 38 y 42 x 47 y 42 x 63 y 42 x 64 y 42 x 65 y 42 x 66 y 42 x 67 y 42 x 68 y 42 x 69 y 42 x 70 y 42 x 71 y 42 x 72 y 42 x 73 y 42 x 74 y 42 x 75 y 42 x 76 y 42 x 77 y 42 x 78 y 42 x 79 y 42 x 80 y 42 x 81 y 42 x 17 y 43 x 18 y 43 x 19 y 43 x 20 y 43 x 21 y 43 x 22 y 43 x 23 y 43 x 24 y 43 x 25 y 43 x 26 y 43 x 27 y 43 x 28 y 43 x 29 y 43 x 30 y 43 x 31 y 43 x 32 y 43 x 33 y 43 x 34 y 43 x 35 y 43 x 36 y 43 x 37 y 43 x 38 y 43 x 39 y 43 x 62 y 43 x 63 y 43 x 64 y 43 x 65 y 43 x 66 y 43 x 67 y 43 x 68 y 43 x 69 y 43 x 70 y 43 x 71 y 43 x 72 y 43 x 73 y 43 x 74 y 43 x 75 y 43 x 76 y 43 x 77 y 43 x 78 y 43 x 79 y 43 x 80 y 43 x 81 y 43 x 82 y 43 x 23 y 44 x 24 y 44 x 25 y 44 x 26 y 44 x 27 y 44 x 28 y 44 x 29 y 44 x 30 y 44 x 31 y 44 x 32 y 44 x 33 y 44 x 34 y 44 x 35 y 44 x 36 y 44 x 37 y 44 x 38 y 44 x 39 y 44 x 40 y 44 x 61 y 44 x 62 y 44 x 63 y 44 x 64 y 44 x 65 y 44 x 66 y 44 x 67 y 44 x 68 y 44 x 69 y 44 x 70 y 44 x 71 y 44 x 72 y 44 x 73 y 44 x 74 y 44 x 75 y 44 x 76 y 44 x 77 y 44 x 78 y 44 x 79 y 44 x 80 y 44 x 81 y 44 x 82 y 44 x 83 y 44 x 26 y 45 x 27 y 45 x 28 y 45 x 29 y 45 x 30 y 45 x 31 y 45 x 32 y 45 x 33 y 45 x 34 y 45 x 35 y 45 x 36 y 45 x 37 y 45 x 38 y 45 x 39 y 45 x 40 y 45 x 41 y 45 x 61 y 45 x 62 y 45 x 63 y 45 x 64 y 45 x 65 y 45 x 66 y 45 x 67 y 45 x 68 y 45 x 69 y 45 x 70 y 45 x 71 y 45 x 72 y 45 x 73 y 45 x 74 y 45 x 75 y 45 x 76 y 45 x 77 y 45 x 78 y 45 x 79 y 45 x 80 y 45 x 81 y 45 x 82 y 45 x 83 y 45 x 84 y 45 x 25 y 46 x 26 y 46 x 27 y 46 x 28 y 46 x 29 y 46 x 30 y 46 x 31 y 46 x 32 y 46 x 33 y 46 x 34 y 46 x 35 y 46 x 36 y 46 x 37 y 46 x 38 y 46 x 39 y 46 x 40 y 46 x 41 y 46 x 42 y 46 x 60 y 46 x 61 y 46 x 62 y 46 x 63 y 46 x 64 y 46 x 65 y 46 x 66 y 46 x 67 y 46 x 68 y 46 x 69 y 46 x 70 y 46 x 71 y 46 x 72 y 46 x 73 y 46 x 74 y 46 x 75 y 46 x 76 y 46 x 77 y 46 x 78 y 46 x 79 y 46 x 80 y 46 x 81 y 46 x 82 y 46 x 83 y 46 x 84 y 46 x 85 y 46 x 25 y 47 x 26 y 47 x 27 y 47 x 28 y 47 x 29 y 47 x 30 y 47 x 31 y 47 x 32 y 47 x 33 y 47 x 34 y 47 x 35 y 47 x 36 y 47 x 37 y 47 x 38 y 47 x 39 y 47 x 40 y 47 x 41 y 47 x 42 y 47 x 43 y 47 x 60 y 47 x 61 y 47 x 62 y 47 x 63 y 47 x 64 y 47 x 65 y 47");
            } else {
                System.out.println("failed to connect");
            }
            System.out.println("connection complete");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + " Error");
        }*/
    }

    //herunder er metoden til at ændre størrelsen på billedet
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
//Den konvertere pixelværdierne til et 2d array

    public static int[][] convertToArray(BufferedImage image) {

        if (image == null || image.getWidth() == 0 || image.getHeight() == 0) {
            return null;
        }
        // This returns bytes of data starting from the top left of the bitmap
        // image and goes down.
        // Top to bottom. Left to right.
        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

        final int width = image.getWidth();
        final int height = image.getHeight();

        int[][] result = new int[height][width];

        boolean done = false;
        boolean alreadyWentToNextByte;
        int byteIndex = 0;
        int row = 0;
        int col = 0;
        int numBits = 0;
        byte currentByte = pixels[byteIndex];
        while (!done) {
            alreadyWentToNextByte = false;

            result[row][col] = (currentByte & 0x80) >> 7;
            currentByte = (byte) (((int) currentByte) << 1);
            numBits++;

            if ((row == height - 1) && (col == width - 1)) {
                done = true;
            } else {
                col++;

                if (numBits == 8) {
                    currentByte = pixels[++byteIndex];
                    numBits = 0;
                    alreadyWentToNextByte = true;
                }

                if (col == width) {
                    row++;
                    col = 0;

                    if (!alreadyWentToNextByte) {
                        currentByte = pixels[++byteIndex];
                        numBits = 0;
                    }
                }
            }
        }

        return result;
    }
    //her udskriver den 2d arrayet til en fil, som så bliver udskrevet som en tekstfil

    public static int[][] compute(File file) throws FileNotFoundException {
        try {
            BufferedImage image = ImageIO.read(file);
            Raster raster = image.getData();
            int w = raster.getWidth(), h = raster.getHeight();

            int pixels[][] = new int[w][h];
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {

                    pixels[x][y] = raster.getSample(x, y, 0);
                }
            }
            String stringx;
           // String stringy;
            try (//her bliver tekstfilen udskrevet
                    PrintStream output = new PrintStream(new File("C:\\Users\\frede\\Videos\\SDU Robot Diplom\\Semester projekt 1\\billeder\\output.txt"));) {
                for (int y = 0; y < h; y++) {
                    for (int x = 0; x < w; x++) {
                            //  output.print(pixels[x][y] + ", ");
                        if (pixels[x][y] < 125) {

                           stringx="a";
                          //  stringy="a" + y;
                        }
                            else {
                                stringx="b";
                       //         stringy="b" + y;
                            } 
                           /*if (x < 10) {
                                stringx = "x 0" + x;
                            } else {
                                stringx = "x " + x;
                            }
                            if (y < 10) {
                                stringy = " y 0" + y;
                            } else {
                                stringy = " y " + y;

                            }*/
                           
                               output.print(stringx);
                           
                    }
                    
                    output.print("l");
                }

                output.close();
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
            return pixels;

        } catch (IOException e) {
        }
        return null;
    }
}
