package com.giraone.imaging;

import com.giraone.imaging.java2.ProviderJava2D;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;

public class ThumbnailCreationBenchmark {

    private static final ImagingProvider imagingProvider = new ProviderJava2D();

    @Benchmark
    @Fork(value = 1, warmups = 5)
    @BenchmarkMode(Mode.AverageTime)
    public void createThumbnailsJpeg() {
        try {
            createThumbnailJpeg("test-files/image-01.jpg");
            createThumbnailJpeg("test-files/image-02.jpg");
            createThumbnailJpeg("test-files/image-exif-01.jpg");
            createThumbnailJpeg("test-files/image-exif-02.jpg");
            createThumbnailJpeg("test-files/image-exif-03.jpg");
        } catch (Exception exc) {
            System.err.println("Cannot create thumbnail: " + exc.getMessage());
        }
    }

    public void createThumbnailJpeg(String originalFileName) throws Exception {
        final Path originalFile = Path.of(originalFileName);
        final Path thumbnailFile = Files.createTempFile("jmh-imaging-kit-", ".jpg");
        imagingProvider.createThumbNail(originalFile, thumbnailFile, "image/jpeg",
            160, 120, ConversionCommand.CompressionQuality.LOSSY_BEST);
        Files.delete(thumbnailFile);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(ThumbnailCreationBenchmark.class.getSimpleName())
            .forks(1)
            .build();

        new Runner(opt).run();
    }
}
