# Montecarlo Pi

Calculate PI using [Monte Carlo](https://en.wikipedia.org/wiki/Monte_Carlo_method) simulation.

## Maven

### Package compatible with DataProc (image preview)

```BASH
mvn package
```

### Unit test (with covertura)

```BASH
mvn clean scoverage:report
```

## Docker

### Build image

```BASH
docker build -t montecarlopi .
```

### Run image simple

```BASH
docker run -it --rm \
  -e NUM=999999 \
  -e URL=test.csv \
  montecarlopi
```
### Run with GCS save

```BASH
docker run -it --rm -v $HOME/.config/gcloud:/gcp \
  -e GOOGLE_APPLICATION_CREDENTIALS="/gcp/application_default_credentials.json" \
  -e NUM=999999999 \
  -e URL=gs://bucket/test.csv \
  montecarlopi
```